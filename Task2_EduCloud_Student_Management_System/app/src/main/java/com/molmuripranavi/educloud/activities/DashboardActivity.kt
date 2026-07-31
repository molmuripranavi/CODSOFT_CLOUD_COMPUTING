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

    private lateinit var txtEmail: TextView

    private lateinit var cardProfile: CardView
    private lateinit var cardCourses: CardView
    private lateinit var cardAttendance: CardView
    private lateinit var cardGrades: CardView
    private lateinit var cardLeave: CardView
    private lateinit var cardHistory: CardView
    private lateinit var cardAnnouncements: CardView

    private lateinit var btnLogout: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Robust Fix: Force status bar to branded blue
        window.statusBarColor = android.graphics.Color.parseColor("#1565C0")
        
        setContentView(R.layout.activity_dashboard)

        auth = FirebaseAuth.getInstance()

        txtEmail = findViewById(R.id.txtEmail)

        cardProfile = findViewById(R.id.cardProfile)
        cardCourses = findViewById(R.id.cardCourses)
        cardAttendance = findViewById(R.id.cardAttendance)
        cardGrades = findViewById(R.id.cardGrades)
        cardLeave = findViewById(R.id.cardLeave)
        cardHistory = findViewById(R.id.cardHistory)
        cardAnnouncements = findViewById(R.id.cardAnnouncements)

        btnLogout = findViewById(R.id.btnLogout)

        txtEmail.text = auth.currentUser?.email ?: "Student"

        cardProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        cardCourses.setOnClickListener {
            startActivity(Intent(this, CoursesActivity::class.java))
        }


        cardAttendance.setOnClickListener {
            startActivity(Intent(this, AttendanceActivity::class.java))
        }

        cardGrades.setOnClickListener {
            startActivity(Intent(this, GradesActivity::class.java))
        }

        cardLeave.setOnClickListener {
            startActivity(Intent(this, ApplyLeaveActivity::class.java))
        }

        cardHistory.setOnClickListener {
            startActivity(Intent(this, LeaveHistoryActivity::class.java))
        }

        cardAnnouncements.setOnClickListener {
            startActivity(Intent(this, AnnouncementsActivity::class.java))
        }

        btnLogout.setOnClickListener {

            auth.signOut()

            val intent = Intent(this, RoleSelectionActivity::class.java)

            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
            finish()
        }
    }
}
