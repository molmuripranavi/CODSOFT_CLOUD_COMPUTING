package com.molmuripranavi.smartbuscloud.activities.admin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore
import com.molmuripranavi.smartbuscloud.R
import com.molmuripranavi.smartbuscloud.adapters.AdminBusAdapter
import com.molmuripranavi.smartbuscloud.models.Bus
import java.util.Locale

class ManageBusActivity : AppCompatActivity() {

    private lateinit var recyclerBuses: RecyclerView
    private lateinit var etSearch: TextInputEditText
    private lateinit var adapter: AdminBusAdapter

    private val busList = ArrayList<Bus>()
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_bus)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener { finish() }

        recyclerBuses = findViewById(R.id.recyclerBuses)
        etSearch = findViewById(R.id.etSearch)

        recyclerBuses.layoutManager = LinearLayoutManager(this)
        adapter = AdminBusAdapter(this, busList)
        recyclerBuses.adapter = adapter

        loadBuses()

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterBuses(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun loadBuses() {
        firestore.collection("buses")
            .get()
            .addOnSuccessListener { documents ->
                busList.clear()
                for (document in documents) {
                    val bus = document.toObject(Bus::class.java)
                    bus.id = document.id
                    busList.add(bus)
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to load buses", Toast.LENGTH_SHORT).show()
            }
    }

    private fun filterBuses(query: String) {
        val filteredList = ArrayList<Bus>()
        for (bus in busList) {
            if (bus.busName.lowercase(Locale.getDefault()).contains(query.lowercase(Locale.getDefault())) ||
                bus.from.lowercase(Locale.getDefault()).contains(query.lowercase(Locale.getDefault())) ||
                bus.to.lowercase(Locale.getDefault()).contains(query.lowercase(Locale.getDefault()))
            ) {
                filteredList.add(bus)
            }
        }
        adapter.updateList(filteredList)
    }

    override fun onResume() {
        super.onResume()
        loadBuses()
    }
}
