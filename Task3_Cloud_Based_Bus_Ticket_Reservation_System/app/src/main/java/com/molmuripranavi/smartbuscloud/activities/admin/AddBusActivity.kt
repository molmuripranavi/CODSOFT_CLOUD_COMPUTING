package com.molmuripranavi.smartbuscloud.activities.admin

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore
import com.molmuripranavi.smartbuscloud.R
import com.molmuripranavi.smartbuscloud.models.Bus

class AddBusActivity : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_bus)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener { finish() }

        firestore = FirebaseFirestore.getInstance()

        val etBusName = findViewById<TextInputEditText>(R.id.etBusName)
        val etFrom = findViewById<TextInputEditText>(R.id.etFrom)
        val etTo = findViewById<TextInputEditText>(R.id.etTo)
        val etDeparture = findViewById<TextInputEditText>(R.id.etDeparture)
        val etArrival = findViewById<TextInputEditText>(R.id.etArrival)
        val etFare = findViewById<TextInputEditText>(R.id.etFare)
        val etSeats = findViewById<TextInputEditText>(R.id.etSeats)
        val spBusType = findViewById<AutoCompleteTextView>(R.id.spBusType)
        val btnAddBus = findViewById<MaterialButton>(R.id.btnAddBus)

        val busTypes = arrayOf(
            "AC Sleeper",
            "Non AC Sleeper",
            "AC Seater",
            "Non AC Seater",
            "Luxury",
            "Super Luxury"
        )

        spBusType.setAdapter(
            ArrayAdapter(this, android.R.layout.simple_list_item_1, busTypes)
        )

        btnAddBus.setOnClickListener {

            val busName = etBusName.text.toString().trim()
            val from = etFrom.text.toString().trim()
            val to = etTo.text.toString().trim()
            val departure = etDeparture.text.toString().trim()
            val arrival = etArrival.text.toString().trim()
            val fare = etFare.text.toString().trim()
            val seats = etSeats.text.toString().trim()
            val busType = spBusType.text.toString().trim()

            if (busName.isEmpty() ||
                from.isEmpty() ||
                to.isEmpty() ||
                departure.isEmpty() ||
                arrival.isEmpty() ||
                fare.isEmpty() ||
                seats.isEmpty() ||
                busType.isEmpty()
            ) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val docRef = firestore.collection("buses").document()

            val bus = Bus(
                id = docRef.id,
                busName = busName,
                from = from,
                to = to,
                departure = departure,
                arrival = arrival,
                fare = fare.toLong(),
                availableSeats = seats.toLong(),
                busType = busType
            )

            docRef.set(bus)
                .addOnSuccessListener {

                    Toast.makeText(
                        this,
                        "Bus Added Successfully",
                        Toast.LENGTH_SHORT
                    ).show()

                    finish()
                }
                .addOnFailureListener {

                    Toast.makeText(
                        this,
                        "Failed to Add Bus",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }
}