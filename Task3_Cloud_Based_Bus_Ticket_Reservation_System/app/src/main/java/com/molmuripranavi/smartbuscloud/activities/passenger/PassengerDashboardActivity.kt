package com.molmuripranavi.smartbuscloud.activities.passenger

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.molmuripranavi.smartbuscloud.R
import com.molmuripranavi.smartbuscloud.activities.authentication.PassengerLoginActivity

class PassengerDashboardActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passenger_dashboard)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // Dashboard views
        val txtName = findViewById<TextView>(R.id.txtName)

        val cardSearchBus =
            findViewById<MaterialCardView>(R.id.cardSearchBus)

        val cardBookings =
            findViewById<MaterialCardView>(R.id.cardBookings)

        val cardProfile =
            findViewById<MaterialCardView>(R.id.cardProfile)

        val cardLogout =
            findViewById<MaterialCardView>(R.id.cardLogout)

        // Load passenger name
        auth.currentUser?.uid?.let { uid ->

            firestore.collection("users")
                .document(uid)
                .get()
                .addOnSuccessListener { document ->

                    if (document.exists()) {

                        val name = document.getString("name")

                        if (!name.isNullOrEmpty()) {
                            txtName.text = getString(R.string.passenger_greeting, name)
                        }
                    }
                }
        }

        // SEARCH BUS
        cardSearchBus.setOnClickListener {

            val intent = Intent(
                this,
                SearchBusActivity::class.java
            )

            startActivity(intent)
        }

        // MY BOOKINGS
        cardBookings.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    MyBookingsActivity::class.java
                )
            )
        }

        // MY PROFILE
        cardProfile.setOnClickListener {

            val intent = Intent(
                this,
                ProfileActivity::class.java
            )

            startActivity(intent)
        }

        // LOGOUT
        cardLogout.setOnClickListener {

            auth.signOut()

            val intent = Intent(
                this,
                PassengerLoginActivity::class.java
            )

            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)

            finish()
        }
    }
}