package com.molmuripranavi.smartbuscloud.activities.admin

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.molmuripranavi.smartbuscloud.R

class ReportsActivity : AppCompatActivity() {

    private lateinit var txtTotalBuses: TextView
    private lateinit var txtTotalPassengers: TextView
    private lateinit var txtTotalBookings: TextView
    private lateinit var txtRevenue: TextView
    private lateinit var txtPopularRoute: TextView

    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reports)

        txtTotalBuses = findViewById(R.id.txtTotalBuses)
        txtTotalPassengers = findViewById(R.id.txtTotalPassengers)
        txtTotalBookings = findViewById(R.id.txtTotalBookings)
        txtRevenue = findViewById(R.id.txtRevenue)
        txtPopularRoute = findViewById(R.id.txtPopularRoute)

        loadReports()
    }

    private fun loadReports() {

            // Total Buses
            firestore.collection("buses")
                .get()
                .addOnSuccessListener { documents ->
                    txtTotalBuses.text = "🚌 Total Buses : ${documents.size()}"
                }

            // Total Passengers
            firestore.collection("users")
                .whereEqualTo("role", "Passenger")
                .get()
                .addOnSuccessListener { documents ->
                    txtTotalPassengers.text =
                        "👥 Total Passengers : ${documents.size()}"
                }

            // Bookings + Revenue + Most Booked Route
            firestore.collection("bookings")
                .get()
                .addOnSuccessListener { documents ->

                    txtTotalBookings.text =
                        "🎫 Total Bookings : ${documents.size()}"

                    var revenue = 0L
                    val routeCount = HashMap<String, Int>()

                    for (document in documents) {

                        revenue += document.getLong("fare") ?: 0L

                        val from = document.getString("from") ?: ""
                        val to = document.getString("to") ?: ""

                        val route = "$from → $to"

                        routeCount[route] = (routeCount[route] ?: 0) + 1
                    }

                    txtRevenue.text = "💰 Total Revenue : ₹$revenue"

                    if (routeCount.isNotEmpty()) {

                        val popularRoute =
                            routeCount.maxByOrNull { it.value }

                        txtPopularRoute.text =
                            "📍 Most Booked Route : ${popularRoute?.key}"

                    } else {

                        txtPopularRoute.text =
                            "📍 Most Booked Route : No Bookings"
                    }
                }
        }
    }