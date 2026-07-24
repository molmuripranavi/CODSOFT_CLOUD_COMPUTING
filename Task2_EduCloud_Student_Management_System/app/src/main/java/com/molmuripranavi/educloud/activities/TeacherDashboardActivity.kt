package com.molmuripranavi.educloud.activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.molmuripranavi.educloud.R

class TeacherDashboardActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    private lateinit var txtPendingCount: TextView
    private lateinit var txtApprovedCount: TextView
    private lateinit var txtRejectedCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_dashboard)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val toolbar = findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val txtTeacherEmail = findViewById<TextView>(R.id.txtTeacherEmail)
        txtTeacherEmail.text = auth.currentUser?.email ?: "teacher@gmail.com"

        txtPendingCount = findViewById(R.id.txtPendingCount)
        txtApprovedCount = findViewById(R.id.txtApprovedCount)
        txtRejectedCount = findViewById(R.id.txtRejectedCount)

        loadStatistics()

        findViewById<MaterialCardView>(R.id.cardPending).setOnClickListener {
            startLeaveList("Pending")
        }

        findViewById<MaterialCardView>(R.id.cardApproved).setOnClickListener {
            startLeaveList("Approved")
        }

        findViewById<MaterialCardView>(R.id.cardRejected).setOnClickListener {
            startLeaveList("Rejected")
        }

        findViewById<MaterialCardView>(R.id.cardProfile).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        findViewById<com.google.android.material.button.MaterialButton>(R.id.btnLogout).setOnClickListener {
            auth.signOut()
            val intent = Intent(this, RoleSelectionActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
    private fun loadStatistics() {

        firestore.collection("LeaveRequests")
            .addSnapshotListener { snapshots, e ->
                if (e != null || snapshots == null) return@addSnapshotListener

                var pending = 0
                var approved = 0
                var rejected = 0

                for (doc in snapshots.documents) {
                    when (doc.getString("status")) {
                        "Pending" -> pending++
                        "Approved", "Teacher Approved" -> approved++
                        "Rejected" -> rejected++
                    }
                }

                txtPendingCount.text = pending.toString()
                txtApprovedCount.text = approved.toString()
                txtRejectedCount.text = rejected.toString()
            }
    }
    private fun startLeaveList(status: String) {
        val intent = Intent(this, TeacherLeaveListActivity::class.java)
        intent.putExtra("STATUS_FILTER", status)
        startActivity(intent)
    }
}
