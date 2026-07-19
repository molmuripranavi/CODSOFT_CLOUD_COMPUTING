package com.molmuripranavi.educloud.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.molmuripranavi.educloud.R

class ApplyLeaveActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etDepartment: EditText
    private lateinit var etReason: EditText
    private lateinit var spYear: Spinner
    private lateinit var spSection: Spinner
    private lateinit var btnSubmit: Button

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_leave)

        etName = findViewById(R.id.etName)
        etDepartment = findViewById(R.id.etDepartment)
        etReason = findViewById(R.id.etReason)
        spYear = findViewById(R.id.spYear)
        spSection = findViewById(R.id.spSection)
        btnSubmit = findViewById(R.id.btnSubmit)

        val years = arrayOf(
            "1st Year",
            "2nd Year",
            "3rd Year",
            "4th Year"
        )

        val sections = arrayOf(
            "A",
            "B",
            "C",
            "D",
            "E",
            "F"
        )

        spYear.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            years
        )

        spSection.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            sections
        )

        btnSubmit.setOnClickListener {

            val name = etName.text.toString().trim()
            val department = etDepartment.text.toString().trim()
            val reason = etReason.text.toString().trim()

            if (name.isEmpty() || department.isEmpty() || reason.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val leaveData = hashMapOf(
                "name" to name,
                "department" to department,
                "year" to spYear.selectedItem.toString(),
                "section" to spSection.selectedItem.toString(),
                "reason" to reason,
                "status" to "Pending"
            )

            db.collection("LeaveRequests")
                .add(leaveData)
                .addOnSuccessListener {
                    Toast.makeText(this, "Leave Applied Successfully", Toast.LENGTH_LONG).show()

                    etName.text.clear()
                    etDepartment.text.clear()
                    etReason.text.clear()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed: ${it.message}", Toast.LENGTH_LONG).show()
                }
        }
    }
}