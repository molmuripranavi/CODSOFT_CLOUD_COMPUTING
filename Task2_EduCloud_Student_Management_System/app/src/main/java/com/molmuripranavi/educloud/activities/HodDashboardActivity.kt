package com.molmuripranavi.educloud.activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.google.firebase.auth.FirebaseAuth
import com.molmuripranavi.educloud.R
import com.google.firebase.firestore.FirebaseFirestore

class HodDashboardActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    private lateinit var txtPendingCount: TextView
    private lateinit var txtApprovedCount: TextView
    private lateinit var txtRejectedCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Robust Fix: Force status bar to branded blue
        window.statusBarColor = android.graphics.Color.parseColor("#1565C0")
        
        setContentView(R.layout.activity_hod_dashboard)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val toolbar = findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val txtHodEmail = findViewById<TextView>(R.id.txtHodEmail)
        txtHodEmail.text = auth.currentUser?.email ?: "hod@gmail.com"
        txtPendingCount = findViewById(R.id.txtPendingCount)
        txtApprovedCount = findViewById(R.id.txtApprovedCount)
        txtRejectedCount = findViewById(R.id.txtRejectedCount)

        loadStatistics()

        findViewById<MaterialCardView>(R.id.cardAnalytics).setOnClickListener {
            startActivity(Intent(this, HodAnalyticsActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.cardPending).setOnClickListener {
            startLeaveList("Pending")
        }

        findViewById<MaterialCardView>(R.id.cardApproved).setOnClickListener {
            startLeaveList("Approved")
        }

        findViewById<MaterialCardView>(R.id.cardRejected).setOnClickListener {
            startLeaveList("Rejected")
        }

        // LEAVE ACTION CARDS (Larger buttons in Admin Actions section)
        findViewById<MaterialCardView>(R.id.cardPendingAction).setOnClickListener {
            startLeaveList("Pending")
        }

        findViewById<MaterialCardView>(R.id.cardApprovedAction).setOnClickListener {
            startLeaveList("Approved")
        }

        findViewById<MaterialCardView>(R.id.cardRejectedAction).setOnClickListener {
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

                    val status = doc.getString("status")?.trim()
                    // STRICT SYNC: Only count if document has a timestamp (matches HodLeaveListActivity query)
                    val hasTimestamp = doc.get("timestamp") != null

                    if (hasTimestamp) {
                        when (status) {
                            "Teacher Approved" -> pending++
                            "Approved" -> approved++
                            "Rejected" -> rejected++
                        }
                    }
                }

                txtPendingCount.text = pending.toString()
                txtApprovedCount.text = approved.toString()
                txtRejectedCount.text = rejected.toString()
            }
    }
    private fun startLeaveList(status: String) {
        val intent = Intent(this, HodLeaveListActivity::class.java)
        intent.putExtra("STATUS_FILTER", status)
        startActivity(intent)
    }
}
