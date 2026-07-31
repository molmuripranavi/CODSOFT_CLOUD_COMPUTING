package com.molmuripranavi.smartbuscloud.activities.passenger

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.firebase.firestore.FirebaseFirestore
import com.molmuripranavi.smartbuscloud.R
import com.molmuripranavi.smartbuscloud.adapters.SeatAdapter
import com.molmuripranavi.smartbuscloud.models.Seat

class SeatSelectionActivity : AppCompatActivity() {

    private lateinit var recyclerSeats: RecyclerView
    private lateinit var txtBusName: TextView
    private lateinit var txtRoute: TextView
    private lateinit var txtSelectedSeat: TextView
    private lateinit var txtFare: TextView
    private lateinit var btnContinue: MaterialButton

    private lateinit var adapter: SeatAdapter

    private val seatList = ArrayList<Seat>()

    private val firestore = FirebaseFirestore.getInstance()

    private val selectedSeats = ArrayList<String>()

    private val MAX_SEATS = 6

    private var fare = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)

        txtBusName = findViewById(R.id.txtBusName)
        txtRoute = findViewById(R.id.txtRoute)
        txtSelectedSeat = findViewById(R.id.txtSelectedSeat)
        txtFare = findViewById(R.id.txtFare)
        btnContinue = findViewById(R.id.btnContinue)
        recyclerSeats = findViewById(R.id.recyclerSeats)

        val busId = intent.getStringExtra("busId") ?: ""
        val busName = intent.getStringExtra("busName") ?: ""
        val from = intent.getStringExtra("from") ?: ""
        val to = intent.getStringExtra("to") ?: ""

        fare = intent.getLongExtra("fare", 0)

        txtBusName.text = busName
        txtRoute.text = getString(R.string.route_format, from, to)
        txtFare.text = getString(R.string.fare_format, fare)

        generateSeats()

        adapter = SeatAdapter(seatList) { seat ->

            if (seat.isSelected) {

                if (selectedSeats.size >= MAX_SEATS) {

                    seat.isSelected = false
                    adapter.notifyDataSetChanged()

                    Toast.makeText(
                        this,
                        "Maximum $MAX_SEATS seats can be booked at once.",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@SeatAdapter
                }

                selectedSeats.add(seat.seatNumber)

            } else {

                selectedSeats.remove(seat.seatNumber)
            }

            txtSelectedSeat.text =
                "Selected Seats (${selectedSeats.size}/$MAX_SEATS): ${
                    selectedSeats.joinToString(", ")
                }"

            val totalFare = fare * selectedSeats.size

            txtFare.text = "Total Fare : ₹$totalFare"
        }

        recyclerSeats.layoutManager =
            GridLayoutManager(this, 4)

        recyclerSeats.adapter = adapter

        loadBookedSeats(busId)

        btnContinue.setOnClickListener {

            if (selectedSeats.isEmpty()) {

                Toast.makeText(
                    this,
                    "Please select at least one seat.",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val intent = Intent(
                this,
                BookingConfirmationActivity::class.java
            )

            intent.putExtra("busId", busId)
            intent.putExtra("busName", busName)
            intent.putExtra("from", from)
            intent.putExtra("to", to)
            val totalFare = fare * selectedSeats.size

            intent.putExtra("fare", totalFare)

            intent.putExtra(
                "seatNumber",
                selectedSeats.joinToString(", ")
            )

            startActivity(intent)
        }
    }

    private fun generateSeats() {

        seatList.clear()

        val rows = arrayOf(
            "A","B","C","D","E",
            "F","G","H","I","J"
        )

        for (row in rows) {

            for (number in 1..4) {

                seatList.add(
                    Seat(
                        "$row$number",
                        false,
                        false
                    )
                )
            }
        }
    }

    private fun loadBookedSeats(busId: String) {

        firestore.collection("bookings")
            .whereEqualTo("busId", busId)
            .get()
            .addOnSuccessListener { documents ->

                for (document in documents) {

                    val bookedSeats =
                        document.getString("seatNumber") ?: ""

                    val seatArray = bookedSeats.split(",")

                    seatArray.forEach { booked ->

                        val seatNumber = booked.trim()

                        seatList.forEach {

                            if (it.seatNumber == seatNumber) {
                                it.isBooked = true
                            }
                        }
                    }
                }

                adapter.notifyDataSetChanged()
            }
    }
}