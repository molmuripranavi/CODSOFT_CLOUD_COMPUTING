package com.molmuripranavi.educloud.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.firestore.FirebaseFirestore
import com.molmuripranavi.educloud.R
import com.molmuripranavi.educloud.adapters.StudentListAdapter
import com.molmuripranavi.educloud.models.StudentProfile

class TeacherManageStudentsActivity : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    private val studentList = ArrayList<StudentProfile>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_manage_students)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        firestore = FirebaseFirestore.getInstance()
        recyclerView = findViewById(R.id.recyclerStudents)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = StudentListAdapter(studentList) { student ->
            val intent = Intent(this, TeacherUpdateRecordActivity::class.java)
            intent.putExtra("ROLL_NUMBER", student.rollNumber)
            intent.putExtra("STUDENT_NAME", student.name)
            intent.putExtra("EMAIL", student.email)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        loadStudents()
    }

    private fun loadStudents() {
        firestore.collection("StudentProfiles")
            .get()
            .addOnSuccessListener { documents ->
                studentList.clear()
                for (doc in documents) {
                    val student = doc.toObject(StudentProfile::class.java)
                    studentList.add(student)
                }
                recyclerView.adapter?.notifyDataSetChanged()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to load students", Toast.LENGTH_SHORT).show()
            }
    }
}
