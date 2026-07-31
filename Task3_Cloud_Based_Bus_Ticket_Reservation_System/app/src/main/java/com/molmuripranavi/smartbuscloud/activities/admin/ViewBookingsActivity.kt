package com.molmuripranavi.smartbuscloud.activities.admin

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.molmuripranavi.smartbuscloud.R
import com.molmuripranavi.smartbuscloud.adapters.AdminBookingAdapter
import com.molmuripranavi.smartbuscloud.models.Booking

class ViewBookingsActivity : AppCompatActivity() {

    private lateinit var recyclerBookings: RecyclerView
    private lateinit var txtNoBookings: TextView
    private lateinit var adapter: AdminBookingAdapter

    private val bookingList = ArrayList<Booking>()

    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_bookings)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener { finish() }

        recyclerBookings = findViewById(R.id.recyclerBookings)
        txtNoBookings = findViewById(R.id.txtNoBookings)

        recyclerBookings.layoutManager = LinearLayoutManager(this)

        adapter = AdminBookingAdapter(this, bookingList)

        recyclerBookings.adapter = adapter

        loadBookings()
    }

    private fun loadBookings() {

        firestore.collection("bookings")
            .orderBy("bookingTime")
            .get()
            .addOnSuccessListener { documents ->

                bookingList.clear()

                for (document in documents) {

                    val booking = document.toObject(Booking::class.java)
                    booking.id = document.id
                    bookingList.add(booking)
                }

                if (bookingList.isEmpty()) {
                    txtNoBookings.visibility = View.VISIBLE
                    recyclerBookings.visibility = View.GONE
                } else {
                    txtNoBookings.visibility = View.GONE
                    recyclerBookings.visibility = View.VISIBLE
                }

                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }

    override fun onResume() {
        super.onResume()
        loadBookings()
    }
}
