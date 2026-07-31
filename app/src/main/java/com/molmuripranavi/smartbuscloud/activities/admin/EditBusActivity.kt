package com.molmuripranavi.smartbuscloud.activities.admin

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore
import com.molmuripranavi.smartbuscloud.R

class EditBusActivity : AppCompatActivity() {

    private lateinit var etBusName: TextInputEditText
    private lateinit var etFrom: TextInputEditText
    private lateinit var etTo: TextInputEditText
    private lateinit var etDeparture: TextInputEditText
    private lateinit var etArrival: TextInputEditText
    private lateinit var etFare: TextInputEditText
    private lateinit var etSeats: TextInputEditText
    private lateinit var etBusType: TextInputEditText
    private lateinit var btnUpdate: MaterialButton

    private lateinit var firestore: FirebaseFirestore

    private var busId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_bus)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener { finish() }

        firestore = FirebaseFirestore.getInstance()

        etBusName = findViewById(R.id.etBusName)
        etFrom = findViewById(R.id.etFrom)
        etTo = findViewById(R.id.etTo)
        etDeparture = findViewById(R.id.etDeparture)
        etArrival = findViewById(R.id.etArrival)
        etFare = findViewById(R.id.etFare)
        etSeats = findViewById(R.id.etSeats)
        etBusType = findViewById(R.id.etBusType)
        btnUpdate = findViewById(R.id.btnUpdate)

        busId = intent.getStringExtra("busId") ?: ""

        etBusName.setText(intent.getStringExtra("busName"))
        etFrom.setText(intent.getStringExtra("from"))
        etTo.setText(intent.getStringExtra("to"))
        etDeparture.setText(intent.getStringExtra("departure"))
        etArrival.setText(intent.getStringExtra("arrival"))
        etFare.setText(intent.getLongExtra("fare", 0).toString())
        etSeats.setText(intent.getLongExtra("availableSeats", 0).toString())
        etBusType.setText(intent.getStringExtra("busType"))

        btnUpdate.setOnClickListener {

            val updatedBus = hashMapOf<String, Any>(
                "busName" to etBusName.text.toString().trim(),
                "from" to etFrom.text.toString().trim(),
                "to" to etTo.text.toString().trim(),
                "departure" to etDeparture.text.toString().trim(),
                "arrival" to etArrival.text.toString().trim(),
                "fare" to (etFare.text.toString().toLongOrNull() ?: 0),
                "availableSeats" to (etSeats.text.toString().toLongOrNull() ?: 0),
                "busType" to etBusType.text.toString().trim()
            )

            firestore.collection("buses")
                .document(busId)
                .update(updatedBus)
                .addOnSuccessListener {

                    Toast.makeText(
                        this,
                        "Bus Updated Successfully",
                        Toast.LENGTH_SHORT
                    ).show()

                    finish()
                }
                .addOnFailureListener {

                    Toast.makeText(
                        this,
                        it.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
        }
    }
}