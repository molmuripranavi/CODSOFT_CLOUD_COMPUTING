package com.molmuripranavi.educloud.activities

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.molmuripranavi.educloud.R
import com.molmuripranavi.educloud.models.StudentProfile

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    private lateinit var etName: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etDepartment: TextInputEditText
    private lateinit var etYear: TextInputEditText
    private lateinit var etSection: TextInputEditText
    private lateinit var etStudentType: TextInputEditText

    private lateinit var btnSave: MaterialButton
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Student Profile"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etDepartment = findViewById(R.id.etDepartment)
        etYear = findViewById(R.id.etYear)
        etSection = findViewById(R.id.etSection)
        etStudentType = findViewById(R.id.etStudentType)

        btnSave = findViewById(R.id.btnSave)
        progressBar = findViewById(R.id.progressBar)

        etEmail.setText(auth.currentUser?.email)
        etEmail.isEnabled = false

        loadProfile()

        btnSave.setOnClickListener {
            saveProfile()
        }
    }

    private fun saveProfile() {

        val email = auth.currentUser?.email ?: return

        progressBar.visibility = View.VISIBLE
        btnSave.isEnabled = false

        val profile = StudentProfile(
            name = etName.text.toString().trim(),
            email = email,
            department = etDepartment.text.toString().trim(),
            year = etYear.text.toString().trim(),
            section = etSection.text.toString().trim(),
            studentType = etStudentType.text.toString().trim()
        )

        firestore.collection("StudentProfiles")
            .document(email)
            .set(profile)
            .addOnSuccessListener {

                progressBar.visibility = View.GONE
                btnSave.isEnabled = true

                Toast.makeText(
                    this,
                    "Profile updated successfully.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener {

                progressBar.visibility = View.GONE
                btnSave.isEnabled = true

                Toast.makeText(
                    this,
                    "Failed to update profile.",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    private fun loadProfile() {

        val email = auth.currentUser?.email ?: return

        progressBar.visibility = View.VISIBLE

        firestore.collection("StudentProfiles")
            .document(email)
            .get()
            .addOnSuccessListener { document ->

                progressBar.visibility = View.GONE

                if (document.exists()) {

                    val profile =
                        document.toObject(StudentProfile::class.java)

                    profile?.let {

                        etName.setText(it.name)
                        etDepartment.setText(it.department)
                        etYear.setText(it.year)
                        etSection.setText(it.section)
                        etStudentType.setText(it.studentType)
                    }
                }
            }
            .addOnFailureListener {

                progressBar.visibility = View.GONE

                Toast.makeText(
                    this,
                    "Unable to load profile.",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}