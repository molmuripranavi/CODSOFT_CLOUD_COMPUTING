package com.molmuripranavi.smartbuscloud.activities.passenger

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.molmuripranavi.smartbuscloud.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SearchBusActivity : AppCompatActivity() {

    private lateinit var from: AutoCompleteTextView
    private lateinit var to: AutoCompleteTextView
    private lateinit var date: EditText
    private lateinit var passengers: AutoCompleteTextView
    private lateinit var btnSearch: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_bus)

        from = findViewById(R.id.etFrom)
        to = findViewById(R.id.etTo)
        date = findViewById(R.id.etDate)
        passengers = findViewById(R.id.etPassengers)
        btnSearch = findViewById(R.id.btnSearch)

        loadCities()

        loadPassengerCount()

        openDatePicker()

        searchBus()
    }

    private fun loadCities() {

        val cityList = arrayOf(
            "Hyderabad",
            "Vijayawada",
            "Warangal",
            "Khammam",
            "Karimnagar",
            "Visakhapatnam",
            "Tirupati",
            "Guntur",
            "Nizamabad"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            cityList
        )

        from.setAdapter(adapter)
        to.setAdapter(adapter)
    }

    private fun loadPassengerCount() {

        val passengerList = arrayOf(
            "1",
            "2",
            "3",
            "4",
            "5",
            "6"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            passengerList
        )

        passengers.setAdapter(adapter)
    }

    private fun openDatePicker() {

        date.setOnClickListener {

            val calendar = Calendar.getInstance()

            val dialog = DatePickerDialog(
                this,
                { _, year, month, day ->

                    calendar.set(year, month, day)

                    val format = SimpleDateFormat(
                        "dd/MM/yyyy",
                        Locale.getDefault()
                    )

                    date.setText(format.format(calendar.time))

                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )

            dialog.show()
        }
    }

    private fun searchBus() {

        btnSearch.setOnClickListener {

            val fromCity = from.text.toString().trim()
            val toCity = to.text.toString().trim()
            val journeyDate = date.text.toString().trim()
            val passengerCount = passengers.text.toString().trim()

            if (fromCity.isEmpty()) {
                from.error = "Select source city"
                return@setOnClickListener
            }

            if (toCity.isEmpty()) {
                to.error = "Select destination city"
                return@setOnClickListener
            }

            if (journeyDate.isEmpty()) {
                date.error = "Select journey date"
                return@setOnClickListener
            }

            if (passengerCount.isEmpty()) {
                passengers.error = "Select passengers"
                return@setOnClickListener
            }

            if (fromCity.equals(toCity, true)) {

                Toast.makeText(
                    this,
                    "Source and Destination cannot be same",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            Toast.makeText(this, "Searching for buses...", Toast.LENGTH_SHORT).show()

            val intent = Intent(
                this,
                BusListActivity::class.java
            )

            intent.putExtra("from", fromCity)
            intent.putExtra("to", toCity)
            intent.putExtra("date", journeyDate)
            intent.putExtra("passengers", passengerCount)

            startActivity(intent)
        }
    }
}