package com.molmuripranavi.smartbuscloud.activities.admin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore
import com.molmuripranavi.smartbuscloud.R
import com.molmuripranavi.smartbuscloud.adapters.PassengerAdapter
import com.molmuripranavi.smartbuscloud.models.Passenger

class ManagePassengersActivity : AppCompatActivity() {

    private lateinit var recyclerPassengers: RecyclerView
    private lateinit var txtNoPassengers: TextView
    private lateinit var txtTotalPassengers: TextView
    private lateinit var etSearchPassenger: TextInputEditText

    private lateinit var adapter: PassengerAdapter

    private val passengerList = ArrayList<Passenger>()
    private val filteredList = ArrayList<Passenger>()

    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_passengers)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener { finish() }

        recyclerPassengers = findViewById(R.id.recyclerPassengers)
        txtNoPassengers = findViewById(R.id.txtNoPassengers)
        txtTotalPassengers = findViewById(R.id.txtTotalPassengers)
        etSearchPassenger = findViewById(R.id.etSearchPassenger)

        recyclerPassengers.layoutManager = LinearLayoutManager(this)

        adapter = PassengerAdapter(this, filteredList)
        recyclerPassengers.adapter = adapter

        loadPassengers()

        etSearchPassenger.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {

                searchPassenger(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun loadPassengers() {

        firestore.collection("users")
            .whereEqualTo("role", "Passenger")
            .get()
            .addOnSuccessListener { documents ->

                passengerList.clear()
                filteredList.clear()

                for (document in documents) {

                    val passenger =
                        document.toObject(Passenger::class.java)

                    passenger.id = document.id

                    passengerList.add(passenger)
                }

                filteredList.addAll(passengerList)

                adapter.notifyDataSetChanged()

                txtTotalPassengers.text =
                    "Total Passengers : ${passengerList.size}"

                if (passengerList.isEmpty()) {

                    txtNoPassengers.visibility = View.VISIBLE
                    recyclerPassengers.visibility = View.GONE

                } else {

                    txtNoPassengers.visibility = View.GONE
                    recyclerPassengers.visibility = View.VISIBLE
                }
            }
    }

    private fun searchPassenger(query: String) {

        filteredList.clear()

        for (passenger in passengerList) {

            if (passenger.name.contains(query, true)
                || passenger.email.contains(query, true)
            ) {

                filteredList.add(passenger)
            }
        }

        adapter.notifyDataSetChanged()

        if (filteredList.isEmpty()) {

            txtNoPassengers.visibility = View.VISIBLE
            recyclerPassengers.visibility = View.GONE

        } else {

            txtNoPassengers.visibility = View.GONE
            recyclerPassengers.visibility = View.VISIBLE
        }
    }
}