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
import com.molmuripranavi.educloud.adapters.GradeAdapter
import com.molmuripranavi.educloud.models.StudentGrade


class GradesActivity : AppCompatActivity() {


    private lateinit var gradesRecyclerView: RecyclerView

    private lateinit var gradeAdapter: GradeAdapter

    private lateinit var firestore: FirebaseFirestore

    private lateinit var auth: FirebaseAuth

    private lateinit var txtNoData: android.widget.TextView


    private val gradeList = ArrayList<StudentGrade>()



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_grades)



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



        gradesRecyclerView =
            findViewById(R.id.gradesRecyclerView)


        txtNoData =
            findViewById(R.id.txtNoData)



        gradesRecyclerView.layoutManager =
            LinearLayoutManager(this)



        gradeAdapter =
            GradeAdapter(gradeList)



        gradesRecyclerView.adapter =
            gradeAdapter



        firestore =
            FirebaseFirestore.getInstance()


        auth =
            FirebaseAuth.getInstance()



        loadGrades()

    }



    private fun loadGrades() {


        val email =
            auth.currentUser?.email ?: return



        firestore.collection("StudentProfiles")
            .document(email)
            .get()
            .addOnSuccessListener { profileDoc ->
                if (profileDoc.exists()) {
                    val rollNumber = profileDoc.getString("rollNumber")?.trim() ?: ""

                    if (rollNumber.isNotEmpty()) {
                        fetchGradesByRollNumber(rollNumber)
                    } else {
                        txtNoData.visibility = android.view.View.VISIBLE
                        txtNoData.text = "Roll number not found in profile"
                    }
                } else {
                    txtNoData.visibility = android.view.View.VISIBLE
                    txtNoData.text = "Student profile not found"
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


    private fun fetchGradesByRollNumber(rollNumber: String) {


        firestore.collection("Grades")

            .whereEqualTo("studentId", rollNumber)

            .get()

            .addOnSuccessListener { documents ->


                gradeList.clear()


                if (documents.isEmpty) {
                    txtNoData.visibility = android.view.View.VISIBLE
                    txtNoData.text = "No grades found for $rollNumber"
                } else {
                    txtNoData.visibility = android.view.View.GONE
                    for (document in documents) {
                        try {
                            val grade = document.toObject(StudentGrade::class.java)
                            grade.id = document.id
                            gradeList.add(grade)
                        } catch (e: Exception) {
                            android.util.Log.e("GradesActivity", "Error parsing grade", e)
                        }
                    }
                }


                gradeAdapter.notifyDataSetChanged()


            }


            .addOnFailureListener {


                Toast.makeText(
                    this,
                    "Failed to load grades",
                    Toast.LENGTH_SHORT
                ).show()

            }

    }


}