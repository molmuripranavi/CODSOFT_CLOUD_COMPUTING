package com.molmuripranavi.smartbuscloud.activities.passenger

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.molmuripranavi.smartbuscloud.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ProfileActivity : AppCompatActivity() {

    private lateinit var imgProfile: ImageView

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var etDob: EditText
    private lateinit var etAddress: EditText

    private lateinit var gender: AutoCompleteTextView

    private lateinit var btnSave: MaterialButton

    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        imgProfile = findViewById(R.id.imgProfile)

        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etPhone = findViewById(R.id.etPhone)
        etDob = findViewById(R.id.etDob)
        etAddress = findViewById(R.id.etAddress)

        gender = findViewById(R.id.etGender)

        btnSave = findViewById(R.id.btnSave)

        loadGender()

        openDatePicker()

        loadProfile()

        saveProfile()
    }

    private fun loadGender() {

        val list = arrayOf(
            "Male",
            "Female",
            "Other"
        )

        gender.setAdapter(
            ArrayAdapter(
                this,
                android.R.layout.simple_dropdown_item_1line,
                list
            )
        )
    }

    private fun openDatePicker() {

        etDob.setOnClickListener {

            val calendar = Calendar.getInstance()

            DatePickerDialog(
                this,
                { _, year, month, day ->

                    calendar.set(year, month, day)

                    val format = SimpleDateFormat(
                        "dd/MM/yyyy",
                        Locale.getDefault()
                    )

                    etDob.setText(format.format(calendar.time))

                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun loadProfile() {

        val uid = auth.currentUser?.uid ?: return

        firestore.collection("users")
            .document(uid)
            .get()
            .addOnSuccessListener {

                etName.setText(it.getString("name"))
                etEmail.setText(it.getString("email"))
                etPhone.setText(it.getString("phone"))
                etDob.setText(it.getString("dob"))
                etAddress.setText(it.getString("address"))
                gender.setText(it.getString("gender"), false)
            }
    }

    private fun saveProfile() {

        btnSave.setOnClickListener {

            val uid = auth.currentUser?.uid ?: return@setOnClickListener

            val map = hashMapOf<String, Any>(
                "name" to etName.text.toString().trim(),
                "email" to etEmail.text.toString().trim(),
                "phone" to etPhone.text.toString().trim(),
                "dob" to etDob.text.toString().trim(),
                "gender" to gender.text.toString().trim(),
                "address" to etAddress.text.toString().trim()
            )

            firestore.collection("users")
                .document(uid)
                .update(map)
                .addOnSuccessListener {

                    Toast.makeText(
                        this,
                        "Profile Updated Successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnFailureListener {

                    Toast.makeText(
                        this,
                        "Failed to Update Profile",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }
}