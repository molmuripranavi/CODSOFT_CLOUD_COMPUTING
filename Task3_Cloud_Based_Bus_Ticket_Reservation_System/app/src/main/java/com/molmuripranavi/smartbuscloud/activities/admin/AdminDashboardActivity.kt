package com.molmuripranavi.smartbuscloud.activities.admin

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.molmuripranavi.smartbuscloud.R
import com.molmuripranavi.smartbuscloud.activities.authentication.RoleSelectionActivity
import android.widget.Toast


class AdminDashboardActivity : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        val txtWelcome = findViewById<TextView>(R.id.txtWelcome)

        val cardAddBus = findViewById<MaterialCardView>(R.id.cardAddBus)
        val cardManageBus = findViewById<MaterialCardView>(R.id.cardManageBus)
        val cardBookings = findViewById<MaterialCardView>(R.id.cardBookings)
        val cardPassengers = findViewById<MaterialCardView>(R.id.cardPassengers)
        val cardReports = findViewById<MaterialCardView>(R.id.cardReports)
        val cardLogout = findViewById<MaterialCardView>(R.id.cardLogout)

        val uid = auth.currentUser?.uid

        if (uid != null) {

            firestore.collection("users")
                .document(uid)
                .get()
                .addOnSuccessListener { document ->

                    if (document.exists()) {

                        val name = document.getString("name")

                        if (!name.isNullOrEmpty()) {
                            txtWelcome.text = "Welcome, $name 👋"
                        }
                    }
                }
        }

        cardAddBus.setOnClickListener {
            startActivity(Intent(this, AddBusActivity::class.java))
        }

        cardManageBus.setOnClickListener {
            startActivity(Intent(this, ManageBusActivity::class.java))
        }

        cardBookings.setOnClickListener {
            startActivity(Intent(this, ViewBookingsActivity::class.java))
        }

        cardPassengers.setOnClickListener {
            startActivity(Intent(this, ManagePassengersActivity::class.java))
        }


        cardReports.setOnClickListener {
            startActivity(Intent(this, ReportsActivity::class.java))
        }

        cardLogout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, RoleSelectionActivity::class.java))
            finish()
        }

    }

}