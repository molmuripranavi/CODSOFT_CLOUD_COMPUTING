package com.molmuripranavi.educloud.activities

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.molmuripranavi.educloud.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var firestore: FirebaseFirestore
    private lateinit var oneTapClient: SignInClient
    private lateinit var signInRequest: BeginSignInRequest

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: MaterialButton
    private lateinit var btnGoogle: MaterialButton
    private var selectedRole = "Student"

    private val googleSignInLauncher =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->

            if (result.resultCode == RESULT_OK) {
                try {
                    val credential = oneTapClient.getSignInCredentialFromIntent(result.data)
                    val idToken = credential.googleIdToken

                    if (idToken != null) {
                        firebaseAuthWithGoogle(idToken)
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Match professional blue theme for status bar
        window.statusBarColor = android.graphics.Color.parseColor("#1565C0")
        
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnGoogle = findViewById(R.id.btnGoogle)

        oneTapClient = Identity.getSignInClient(this)
        selectedRole = intent.getStringExtra("ROLE") ?: "Student"
        val txtWelcome = findViewById<android.widget.TextView>(R.id.txtWelcome)
        val txtSubtitle = findViewById<android.widget.TextView>(R.id.txtSubtitle)

        txtWelcome.text = "$selectedRole Login"

        when (selectedRole) {

            "Student" -> {
                txtSubtitle.text = "Login using Email or Google"
            }

            "Teacher" -> {
                txtSubtitle.text = "Teacher Portal"
            }

            "HOD" -> {
                txtSubtitle.text = "Head of Department Portal"
            }
        }
        if (selectedRole == "Student") {

            btnGoogle.visibility = android.view.View.VISIBLE

        } else {

            btnGoogle.visibility = android.view.View.GONE

        }
        val txtRegister = findViewById<android.widget.TextView>(R.id.txtRegister)
        if (selectedRole == "Student") {

            txtRegister.visibility = android.view.View.VISIBLE

        } else {

            txtRegister.visibility = android.view.View.GONE

        }
        signInRequest =
            BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(
                    BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        .setServerClientId(getString(R.string.default_web_client_id))
                        .setFilterByAuthorizedAccounts(false)
                        .build()
                )
                .setAutoSelectEnabled(false)
                .build()

        btnLogin.setOnClickListener {
            loginUser()
        }

        btnGoogle.setOnClickListener {
            googleLogin()
        }
    }

    private fun loginUser() {

        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (email.isEmpty()) {
            etEmail.error = "Enter Email"
            etEmail.requestFocus()
            return
        }

        if (password.isEmpty()) {
            etPassword.error = "Enter Password"
            etPassword.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {

                if (it.isSuccessful) {

                    checkUserRole()

                }else {

                    Toast.makeText(
                        this,
                        it.exception?.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    private fun googleLogin() {

        oneTapClient.beginSignIn(signInRequest)
            .addOnSuccessListener(this) { result ->

                googleSignInLauncher.launch(
                    androidx.activity.result.IntentSenderRequest
                        .Builder(result.pendingIntent.intentSender)
                        .build()
                )

            }
            .addOnFailureListener {

                Toast.makeText(
                    this,
                    "Google Sign-In Failed",
                    Toast.LENGTH_SHORT
                ).show()

            }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {

        val credential = GoogleAuthProvider.getCredential(idToken, null)

        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) {

                if (it.isSuccessful) {

                    checkUserRole()

                } else {

                    Toast.makeText(
                        this,
                        it.exception?.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
    private fun checkUserRole() {

        val email = auth.currentUser?.email ?: return

        firestore.collection("Users")
            .document(email)
            .get()
            .addOnSuccessListener { document ->

                if (!document.exists()) {

                    Toast.makeText(
                        this,
                        "User profile not found",
                        Toast.LENGTH_LONG
                    ).show()

                    auth.signOut()
                    return@addOnSuccessListener
                }

                val role = document.getString("role") ?: ""

                if (role != selectedRole) {

                    Toast.makeText(
                        this,
                        "This account is registered as $role.\nPlease login through the $role portal.",
                        Toast.LENGTH_LONG
                    ).show()

                    auth.signOut()
                    return@addOnSuccessListener
                }
                FirebaseMessaging.getInstance().token
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            val token = task.result

                            firestore.collection("Users")
                                .document(email)
                                .update("fcmToken", token)
                        }
                    }

                when (role) {

                    "Student" -> {
                        startActivity(Intent(this, DashboardActivity::class.java))
                        finish()
                    }

                    "Teacher" -> {
                        startActivity(Intent(this, TeacherDashboardActivity::class.java))
                        finish()
                    }

                    "HOD" -> {
                        startActivity(Intent(this, HodDashboardActivity::class.java))
                        finish()
                    }
                }

            }
            .addOnFailureListener {

                Toast.makeText(
                    this,
                    it.message,
                    Toast.LENGTH_LONG
                ).show()
            }
    }
    }