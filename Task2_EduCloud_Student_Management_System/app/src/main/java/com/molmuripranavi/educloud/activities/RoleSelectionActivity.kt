package com.molmuripranavi.educloud.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.molmuripranavi.educloud.R

class RoleSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_role_selection)

        val cardStudent = findViewById<CardView>(R.id.cardStudent)
        val cardTeacher = findViewById<CardView>(R.id.cardTeacher)
        val cardHod = findViewById<CardView>(R.id.cardHod)

        // Student
        cardStudent.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("ROLE", "Student")
            startActivity(intent)
        }

// Teacher
        cardTeacher.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("ROLE", "Teacher")
            startActivity(intent)
        }

// HOD
        cardHod.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("ROLE", "HOD")
            startActivity(intent)
        }
    }
}