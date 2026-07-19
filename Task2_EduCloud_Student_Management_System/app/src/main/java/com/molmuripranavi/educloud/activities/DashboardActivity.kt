package com.molmuripranavi.educloud.activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.molmuripranavi.educloud.R

class DashboardActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        auth = FirebaseAuth.getInstance()

        val txtEmail = findViewById<TextView>(R.id.txtEmail)
        val btnLogout = findViewById<MaterialButton>(R.id.btnLogout)
        val cardLeave = findViewById<CardView>(R.id.cardLeave)

        // Show logged-in user's email
        txtEmail.text = auth.currentUser?.email ?: "Student"

        // Open Apply Leave screen
        cardLeave.setOnClickListener {
            startActivity(Intent(this, ApplyLeaveActivity::class.java))
        }

        // Logout
        btnLogout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}