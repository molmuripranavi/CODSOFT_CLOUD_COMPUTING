package com.molmuripranavi.smartbuscloud.activities.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.molmuripranavi.smartbuscloud.R
import com.molmuripranavi.smartbuscloud.activities.admin.AdminDashboardActivity

class AdminLoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val btnLogin = findViewById<MaterialButton>(R.id.btnLogin)

        btnLogin.setOnClickListener {

            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {

                Toast.makeText(
                    this,
                    "Please enter email and password",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {

                    val uid = auth.currentUser?.uid ?: return@addOnSuccessListener

                    firestore.collection("users").document(uid).get()
                        .addOnSuccessListener { document ->
                            val role = document.getString("role")
                            if (role == "Admin") {
                                Toast.makeText(this, "Admin Login Successful", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this, AdminDashboardActivity::class.java))
                                finish()
                            } else {
                                auth.signOut()
                                Toast.makeText(this, "Unauthorized: Admin account only", Toast.LENGTH_SHORT).show()
                            }
                        }
                        .addOnFailureListener {
                            auth.signOut()
                            Toast.makeText(this, "Failed to verify role", Toast.LENGTH_SHORT).show()
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
}