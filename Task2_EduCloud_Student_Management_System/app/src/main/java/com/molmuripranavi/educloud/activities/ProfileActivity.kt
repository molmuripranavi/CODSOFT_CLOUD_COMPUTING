package com.molmuripranavi.educloud.activities

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.card.MaterialCardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.molmuripranavi.educloud.R

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    private lateinit var txtName: TextView
    private lateinit var txtEmail: TextView
    private lateinit var txtRoll: TextView
    private lateinit var txtDepartment: TextView
    private lateinit var txtYear: TextView
    private lateinit var txtSection: TextView
    private lateinit var txtStudentType: TextView
    private lateinit var txtPhone: TextView

    private lateinit var cardAcademicInfo: MaterialCardView
    private lateinit var txtProfileSectionTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Remove manual overrides - rely on Theme for professional blue status bar
        setContentView(R.layout.activity_profile)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        if (toolbar != null) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            toolbar.setNavigationOnClickListener { finish() }
        }

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        txtName = findViewById(R.id.txtName)
        txtEmail = findViewById(R.id.txtEmail)
        txtRoll = findViewById(R.id.txtRoll)
        txtDepartment = findViewById(R.id.txtDepartment)
        txtYear = findViewById(R.id.txtYear)
        txtSection = findViewById(R.id.txtSection)
        txtStudentType = findViewById(R.id.txtStudentType)
        txtPhone = findViewById(R.id.txtPhone)

        cardAcademicInfo = findViewById(R.id.cardAcademicInfo)
        txtProfileSectionTitle = findViewById(R.id.txtProfileSectionTitle)

        loadProfile()
    }

    private fun loadProfile() {
        val email = auth.currentUser?.email ?: return

        firestore.collection("Users").document(email).get()
            .addOnSuccessListener { userDoc ->
                if (userDoc.exists()) {
                    val role = userDoc.getString("role") ?: "Student"
                    val name = userDoc.getString("name") ?: "User"

                    txtName.text = name
                    txtEmail.text = email

                    if (role == "Student") {
                        txtProfileSectionTitle.text = "Academic Profile"
                        loadStudentData(email)
                    } else {
                        // Staff View
                        txtProfileSectionTitle.text = "Staff Information"
                        
                        findViewById<TextView>(R.id.labelRoll).text = "Designation"
                        txtRoll.text = role
                        
                        findViewById<TextView>(R.id.labelDept).text = "Department"
                        txtDepartment.text = "Academic Administration"

                        // Hide student fields
                        findViewById<TextView>(R.id.labelYear).visibility = View.GONE
                        txtYear.visibility = View.GONE
                        findViewById<TextView>(R.id.labelSection).visibility = View.GONE
                        txtSection.visibility = View.GONE
                        findViewById<TextView>(R.id.labelStudentType).visibility = View.GONE
                        txtStudentType.visibility = View.GONE
                        
                        // Contact
                        findViewById<TextView>(R.id.labelPhone).text = "Official Email"
                        txtPhone.text = email
                    }
                }
            }
    }

    private fun loadStudentData(email: String) {
        firestore.collection("StudentProfiles").document(email).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    txtName.text = document.getString("name") ?: "N/A"
                    txtRoll.text = document.getString("rollNumber") ?: "N/A"
                    txtDepartment.text = document.getString("department") ?: "N/A"
                    txtYear.text = document.getString("year") ?: "N/A"
                    txtSection.text = document.getString("section") ?: "N/A"
                    txtStudentType.text = document.getString("studentType") ?: "N/A"
                    txtPhone.text = document.getString("phone") ?: "N/A"
                }
            }
    }
}
