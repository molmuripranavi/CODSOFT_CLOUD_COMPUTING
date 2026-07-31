package com.molmuripranavi.smartbuscloud.activities.passenger

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.molmuripranavi.smartbuscloud.R
import com.molmuripranavi.smartbuscloud.adapters.BusAdapter
import com.molmuripranavi.smartbuscloud.models.Bus

class BusListActivity : AppCompatActivity() {

    private lateinit var recyclerBus: RecyclerView
    private lateinit var txtNoBuses: TextView
    private lateinit var txtRouteTitle: TextView

    private lateinit var adapter: BusAdapter

    private val busList = ArrayList<Bus>()

    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_bus_list)

        firestore = FirebaseFirestore.getInstance()

        recyclerBus = findViewById(R.id.recyclerBus)
        txtNoBuses = findViewById(R.id.txtNoBuses)
        txtRouteTitle = findViewById(R.id.txtRouteTitle)

        recyclerBus.layoutManager =
            LinearLayoutManager(this)

        adapter = BusAdapter(
            this,
            busList
        )

        recyclerBus.adapter = adapter

        val from = intent.getStringExtra("from") ?: ""
        val to = intent.getStringExtra("to") ?: ""
        val date = intent.getStringExtra("date") ?: ""

        if (from.isEmpty() || to.isEmpty()) {

            txtRouteTitle.text = "Invalid Route"

            showNoBuses(
                "Please select a valid source and destination."
            )

            return
        }

        txtRouteTitle.text = "$from → $to"

        loadBuses(from, to, date)
    }

    private fun loadBuses(
        from: String,
        to: String,
        date: String
    ) {

        recyclerBus.visibility = View.GONE
        txtNoBuses.visibility = View.VISIBLE

        txtNoBuses.text = "Searching for buses..."

        firestore.collection("buses")
            .whereEqualTo("from", from)
            .whereEqualTo("to", to)
            .get()
            .addOnSuccessListener { documents ->

                busList.clear()

                for (document in documents) {

                    /*
                     * Read Firestore fields explicitly.
                     * This guarantees that String and Number
                     * fields are handled correctly.
                     */

                    val busName =
                        document.getString("busName")
                            ?: "Bus Service"

                    val busType =
                        document.getString("busType")
                            ?: "Standard Bus"

                    val busFrom =
                        document.getString("from")
                            ?: from

                    val busTo =
                        document.getString("to")
                            ?: to

                    val departure =
                        document.getString("departure")
                            ?: "--"

                    val arrival =
                        document.getString("arrival")
                            ?: "--"

                    val fare =
                        document.getLong("fare")
                            ?: 0L

                    val availableSeats =
                        document.getLong("availableSeats")
                            ?: 0L

                    val bus = Bus(

                        id = document.id,

                        busName = busName,

                        from = busFrom,

                        to = busTo,

                        departure = departure,

                        arrival = arrival,

                        fare = fare,

                        availableSeats = availableSeats,

                        busType = busType
                    )

                    busList.add(bus)
                }

                adapter.notifyDataSetChanged()

                if (busList.isEmpty()) {

                    showNoBuses(
                        "No buses available\nfor this route"
                    )

                } else {

                    txtNoBuses.visibility =
                        View.GONE

                    recyclerBus.visibility =
                        View.VISIBLE
                }
            }
            .addOnFailureListener { exception ->

                showNoBuses(
                    "Unable to load buses"
                )

                Toast.makeText(
                    this,
                    exception.message
                        ?: "Failed to load buses",
                    Toast.LENGTH_LONG
                ).show()
            }
    }

    private fun showNoBuses(message: String) {

        recyclerBus.visibility =
            View.GONE

        txtNoBuses.visibility =
            View.VISIBLE

        txtNoBuses.text = message
    }
}