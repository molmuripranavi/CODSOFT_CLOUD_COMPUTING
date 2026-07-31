package com.molmuripranavi.smartbuscloud.activities.passenger

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.molmuripranavi.smartbuscloud.R
import com.molmuripranavi.smartbuscloud.adapters.BookingAdapter
import com.molmuripranavi.smartbuscloud.models.Booking

class MyBookingsActivity : AppCompatActivity() {

    private lateinit var recyclerBookings: RecyclerView
    private lateinit var txtNoBookings: TextView

    private lateinit var adapter: BookingAdapter

    private val bookingList = ArrayList<Booking>()

    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_bookings)

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        recyclerBookings = findViewById(R.id.recyclerBookings)
        txtNoBookings = findViewById(R.id.txtNoBookings)

        recyclerBookings.layoutManager =
            LinearLayoutManager(this)

        adapter = BookingAdapter(bookingList)

        recyclerBookings.adapter = adapter

        loadBookings()
    }

    private fun loadBookings() {

        val userId = auth.currentUser?.uid ?: return

        firestore.collection("bookings")
            .whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener { documents ->

                bookingList.clear()

                if (documents.isEmpty) {

                    txtNoBookings.visibility = View.VISIBLE
                    txtNoBookings.text = "No bookings found"
                    recyclerBookings.visibility = View.GONE
                    return@addOnSuccessListener
                }

                txtNoBookings.visibility = View.GONE
                recyclerBookings.visibility = View.VISIBLE

                for (document in documents) {

                    val booking = document.toObject(Booking::class.java)
                    booking.id = document.id
                    bookingList.add(booking)
                }

                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e ->

                txtNoBookings.visibility = View.VISIBLE
                txtNoBookings.text = e.message
                recyclerBookings.visibility = View.GONE
            }
    }
}