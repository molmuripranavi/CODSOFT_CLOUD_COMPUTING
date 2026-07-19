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

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var oneTapClient: SignInClient
    private lateinit var signInRequest: BeginSignInRequest

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: MaterialButton
    private lateinit var btnGoogle: MaterialButton

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
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnGoogle = findViewById(R.id.btnGoogle)

        oneTapClient = Identity.getSignInClient(this)

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

                    Toast.makeText(
                        this,
                        "Login Successful",
                        Toast.LENGTH_SHORT
                    ).show()

                    startActivity(
                        Intent(
                            this,
                            DashboardActivity::class.java
                        )
                    )

                    finish()

                } else {

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

                    Toast.makeText(
                        this,
                        "Google Login Successful",
                        Toast.LENGTH_SHORT
                    ).show()

                    startActivity(
                        Intent(
                            this,
                            DashboardActivity::class.java
                        )
                    )

                    finish()

                } else {

                    Toast.makeText(
                        this,
                        it.exception?.message,
                        Toast.LENGTH_LONG
                    ).show()

                }
            }
    }
}