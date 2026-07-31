package com.molmuripranavi.smartbuscloud.activities.passenger

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.molmuripranavi.smartbuscloud.R

class BookingConfirmationActivity : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_confirmation)

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val txtBusName = findViewById<TextView>(R.id.txtBusName)
        val txtRoute = findViewById<TextView>(R.id.txtRoute)
        val txtSeat = findViewById<TextView>(R.id.txtSeat)
        val txtFare = findViewById<TextView>(R.id.txtFare)
        val btnConfirm = findViewById<MaterialButton>(R.id.btnConfirm)

        val busId = intent.getStringExtra("busId") ?: ""
        val busName = intent.getStringExtra("busName") ?: ""
        val from = intent.getStringExtra("from") ?: ""
        val to = intent.getStringExtra("to") ?: ""
        val seat = intent.getStringExtra("seatNumber") ?: ""
        val journeyDate = intent.getStringExtra("date") ?: ""
        val fare = intent.getLongExtra("fare", 0)

        txtBusName.text = busName
        txtRoute.text = getString(R.string.route_format, from, to)
        txtSeat.text = getString(R.string.seat_format, seat)
        txtFare.text = getString(R.string.fare_format, fare)

        btnConfirm.setOnClickListener {

            val userId = auth.currentUser?.uid ?: return@setOnClickListener

            firestore.collection("users")
                .document(userId)
                .get()
                .addOnSuccessListener { userDocument ->

                    val passengerName =
                        userDocument.getString("name") ?: "Passenger"

                    val passengerEmail =
                        userDocument.getString("email")
                            ?: auth.currentUser?.email.orEmpty()

                    val booking = hashMapOf(

                        "userId" to userId,

                        "passengerName" to passengerName,

                        "passengerEmail" to passengerEmail,

                        "busId" to busId,

                        "busName" to busName,

                        "from" to from,

                        "to" to to,

                        "seatNumber" to seat,

                        "journeyDate" to journeyDate,

                        "fare" to fare,

                        "status" to "Confirmed",

                        "bookingTime" to System.currentTimeMillis()
                    )

                    firestore.collection("bookings")
                        .add(booking)
                        .addOnSuccessListener {

                            val seatCount =
                                if (seat.isNotEmpty())
                                    seat.split(",").size
                                else
                                    0

                            firestore.collection("buses")
                                .document(busId)
                                .update(
                                    "availableSeats",
                                    FieldValue.increment(
                                        -seatCount.toLong()
                                    )
                                )

                            Toast.makeText(
                                this,
                                "Booking Successful",
                                Toast.LENGTH_LONG
                            ).show()

                            val intent = Intent(
                                this,
                                PassengerDashboardActivity::class.java
                            )

                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or
                                        Intent.FLAG_ACTIVITY_CLEAR_TASK

                            startActivity(intent)
                            finish()
                        }
                        .addOnFailureListener {

                            Toast.makeText(
                                this,
                                "Booking Failed",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }
                .addOnFailureListener {

                    Toast.makeText(
                        this,
                        "Unable to load passenger details",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }
}