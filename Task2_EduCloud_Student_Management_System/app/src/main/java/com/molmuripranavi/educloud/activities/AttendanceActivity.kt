package com.molmuripranavi.educloud.activities

import android.os.Bundle
import android.widget.Toast

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

import com.molmuripranavi.educloud.R
import com.molmuripranavi.educloud.adapters.AttendanceAdapter
import com.molmuripranavi.educloud.models.StudentAttendance


class AttendanceActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: AttendanceAdapter

    private lateinit var firestore: FirebaseFirestore

    private lateinit var auth: FirebaseAuth

    private lateinit var txtNoData: android.widget.TextView


    private val attendanceList = ArrayList<StudentAttendance>()



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_attendance)



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->

            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }



        recyclerView =
            findViewById(R.id.attendanceRecyclerView)


        txtNoData =
            findViewById(R.id.txtNoData)



        recyclerView.layoutManager =
            LinearLayoutManager(this)



        adapter =
            AttendanceAdapter(attendanceList)



        recyclerView.adapter =
            adapter



        firestore =
            FirebaseFirestore.getInstance()


        auth =
            FirebaseAuth.getInstance()



        loadAttendance()

    }




    private fun loadAttendance() {


        val email =
            auth.currentUser?.email ?: return



        firestore.collection("StudentProfiles")
            .document(email)
            .get()
            .addOnSuccessListener { profileDoc ->
                if (profileDoc.exists()) {
                    val rollNumber = profileDoc.getString("rollNumber")?.trim() ?: ""

                    if (rollNumber.isNotEmpty()) {
                        Toast.makeText(this, "Querying for: $rollNumber", Toast.LENGTH_SHORT).show()
                        fetchAttendanceByRollNumber(rollNumber)
                    } else {
                        txtNoData.visibility = android.view.View.VISIBLE
                        txtNoData.text = "Roll number not found in profile"
                        Toast.makeText(
                            this,
                            "Roll number not found in profile",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    txtNoData.visibility = android.view.View.VISIBLE
                    txtNoData.text = "Student profile not found"
                    Toast.makeText(
                        this,
                        "Student profile not found for $email",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .addOnFailureListener {
                txtNoData.visibility = android.view.View.VISIBLE
                Toast.makeText(
                    this,
                    "Failed to load student profile",
                    Toast.LENGTH_SHORT
                ).show()
            }


    }


    private fun fetchAttendanceByRollNumber(rollNumber: String) {


        firestore.collection("Attendance")

            .whereEqualTo("studentId", rollNumber)

            .get()

            .addOnSuccessListener { documents ->


                attendanceList.clear()


                if (documents.isEmpty) {
                    txtNoData.visibility = android.view.View.VISIBLE
                    txtNoData.text = "No attendance data found for $rollNumber"
                } else {
                    txtNoData.visibility = android.view.View.GONE
                    for (document in documents) {
                        try {
                            val attendance = document.toObject(StudentAttendance::class.java)
                            attendance.id = document.id
                            attendanceList.add(attendance)
                        } catch (e: Exception) {
                            android.util.Log.e("AttendanceActivity", "Error parsing attendance", e)
                        }
                    }
                }


                adapter.notifyDataSetChanged()


            }

            .addOnFailureListener {


                Toast.makeText(
                    this,
                    "Failed to load attendance",
                    Toast.LENGTH_SHORT
                ).show()

            }

    }

}