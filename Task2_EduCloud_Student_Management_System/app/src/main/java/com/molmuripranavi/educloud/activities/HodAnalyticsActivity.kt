package com.molmuripranavi.educloud.activities

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.firestore.FirebaseFirestore
import com.molmuripranavi.educloud.R

class HodAnalyticsActivity : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore

    private lateinit var txtTotalStudents: TextView
    private lateinit var txtAvgAttendance: TextView
    private lateinit var txtGradeA: TextView
    private lateinit var txtGradeB: TextView
    private lateinit var txtGradeC: TextView
    private lateinit var txtGradeD: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // EduCloud branded status bar
        window.statusBarColor =
            android.graphics.Color.parseColor("#1565C0")

        setContentView(R.layout.activity_hod_analytics)

        // Toolbar
        val toolbar =
            findViewById<MaterialToolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        // Views
        txtTotalStudents =
            findViewById(R.id.txtTotalStudents)

        txtAvgAttendance =
            findViewById(R.id.txtAvgAttendance)

        txtGradeA =
            findViewById(R.id.txtGradeA)

        txtGradeB =
            findViewById(R.id.txtGradeB)

        txtGradeC =
            findViewById(R.id.txtGradeC)

        txtGradeD =
            findViewById(R.id.txtGradeD)

        // Firestore
        firestore =
            FirebaseFirestore.getInstance()

        loadAnalytics()
    }

    private fun loadAnalytics() {

        loadStudentCount()

        loadAttendanceAnalytics()

        loadGradeAnalytics()
    }

    // ----------------------------------------------------
    // TOTAL STUDENTS
    // ----------------------------------------------------

    private fun loadStudentCount() {

        firestore.collection("StudentProfiles")
            .get()
            .addOnSuccessListener { documents ->

                txtTotalStudents.text =
                    documents.size().toString()
            }
            .addOnFailureListener {

                txtTotalStudents.text = "0"

                Toast.makeText(
                    this,
                    "Unable to load student count",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    // ----------------------------------------------------
    // ATTENDANCE ANALYTICS
    // ----------------------------------------------------

    private fun loadAttendanceAnalytics() {

        firestore.collection("Attendance")
            .get()
            .addOnSuccessListener { documents ->

                var totalPercentage = 0f
                var count = 0

                for (doc in documents) {

                    val percentage =
                        doc.getString("percentage")
                            ?.replace("%", "")
                            ?.trim()
                            ?.toFloatOrNull()

                    if (percentage != null) {

                        totalPercentage += percentage

                        count++
                    }
                }

                val average =
                    if (count > 0) {
                        totalPercentage / count
                    } else {
                        0f
                    }

                txtAvgAttendance.text =
                    String.format("%.1f%%", average)
            }
            .addOnFailureListener {

                txtAvgAttendance.text = "0.0%"

                Toast.makeText(
                    this,
                    "Unable to load attendance data",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    // ----------------------------------------------------
    // GRADE ANALYTICS
    // ----------------------------------------------------

    private fun loadGradeAnalytics() {

        firestore.collection("Grades")
            .get()
            .addOnSuccessListener { documents ->

                var gradeA = 0
                var gradeB = 0
                var gradeC = 0
                var gradeD = 0

                for (doc in documents) {

                    val grade =
                        doc.getString("grade")
                            ?.trim()
                            ?.uppercase()

                    when (grade) {

                        "A",
                        "A+" -> {
                            gradeA++
                        }

                        "B",
                        "B+" -> {
                            gradeB++
                        }

                        "C" -> {
                            gradeC++
                        }

                        "D",
                        "F" -> {
                            gradeD++
                        }
                    }
                }

                txtGradeA.text =
                    "$gradeA Students"

                txtGradeB.text =
                    "$gradeB Students"

                txtGradeC.text =
                    "$gradeC Students"

                txtGradeD.text =
                    "$gradeD Students"
            }
            .addOnFailureListener {

                Toast.makeText(
                    this,
                    "Unable to load grade data",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}