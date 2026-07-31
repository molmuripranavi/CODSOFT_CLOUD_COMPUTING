package com.molmuripranavi.educloud.activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.molmuripranavi.educloud.R

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    private lateinit var etName: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText

    private lateinit var btnRegister: MaterialButton

    private lateinit var txtTitle: TextView
    private lateinit var txtSubtitle: TextView

    private var selectedRole = "Student"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        selectedRole = intent.getStringExtra("ROLE") ?: "Student"

        txtTitle = findViewById(R.id.txtTitle)
        txtSubtitle = findViewById(R.id.txtSubtitle)

        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)

        btnRegister = findViewById(R.id.btnRegister)

        txtTitle.text = "Create $selectedRole Account"
        txtSubtitle.text = "Register as $selectedRole"

        btnRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {

        val name = etName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (name.isEmpty()) {
            etName.error = "Enter Name"
            return
        }

        if (email.isEmpty()) {
            etEmail.error = "Enter Email"
            return
        }

        if (password.length < 6) {
            etPassword.error = "Minimum 6 characters"
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {

                val user = hashMapOf(
                    "name" to name,
                    "email" to email,
                    "role" to selectedRole
                )

                firestore.collection("Users")
                    .document(email)
                    .set(user)
                    .addOnSuccessListener {

                        Toast.makeText(
                            this,
                            "$selectedRole Registered Successfully",
                            Toast.LENGTH_SHORT
                        ).show()

                        startActivity(
                            Intent(this, LoginActivity::class.java)
                                .putExtra("ROLE", selectedRole)
                        )

                        finish()

                    }
                    .addOnFailureListener {

                        Toast.makeText(
                            this,
                            it.message,
                            Toast.LENGTH_LONG
                        ).show()
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