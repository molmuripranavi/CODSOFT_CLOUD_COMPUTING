package com.molmuripranavi.educloud.activities

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.firebase.firestore.FirebaseFirestore
import com.molmuripranavi.educloud.R

class TeacherUpdateRecordActivity : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var rollNumber: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_update_record)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        firestore = FirebaseFirestore.getInstance()
        rollNumber = intent.getStringExtra("ROLL_NUMBER") ?: ""
        val studentName = intent.getStringExtra("STUDENT_NAME") ?: ""

        findViewById<TextView>(R.id.txtStudentInfo).text = "Updating for: $studentName ($rollNumber)"

        // Attendance
        val etSubAttendance = findViewById<EditText>(R.id.etSubjectAttendance)
        val etAttended = findViewById<EditText>(R.id.etAttended)
        val etTotal = findViewById<EditText>(R.id.etTotal)
        val btnUpdateAttendance = findViewById<MaterialButton>(R.id.btnUpdateAttendance)

        btnUpdateAttendance.setOnClickListener {
            val subject = etSubAttendance.text.toString().trim()
            val attended = etAttended.text.toString().trim()
            val total = etTotal.text.toString().trim()

            if (subject.isNotEmpty() && attended.isNotEmpty() && total.isNotEmpty()) {
                val percentage = (attended.toFloat() / total.toFloat() * 100).toInt().toString() + "%"
                val data = hashMapOf(
                    "studentId" to rollNumber,
                    "subjectName" to subject,
                    "attendedClasses" to attended,
                    "totalClasses" to total,
                    "percentage" to percentage
                )
                firestore.collection("Attendance").add(data)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Attendance Updated", Toast.LENGTH_SHORT).show()
                        etSubAttendance.text.clear()
                        etAttended.text.clear()
                        etTotal.text.clear()
                    }
            }
        }

        // Grades
        val etSubGrade = findViewById<EditText>(R.id.etSubjectGrade)
        val etInternal = findViewById<EditText>(R.id.etInternal)
        val etExternal = findViewById<EditText>(R.id.etExternal)
        val etGradeValue = findViewById<EditText>(R.id.etGrade)
        val btnUpdateGrade = findViewById<MaterialButton>(R.id.btnUpdateGrade)

        btnUpdateGrade.setOnClickListener {
            val subject = etSubGrade.text.toString().trim()
            val internal = etInternal.text.toString().trim()
            val external = etExternal.text.toString().trim()
            val gradeStr = etGradeValue.text.toString().trim()

            if (subject.isNotEmpty() && internal.isNotEmpty() && external.isNotEmpty() && gradeStr.isNotEmpty()) {
                val data = hashMapOf(
                    "studentId" to rollNumber,
                    "subjectName" to subject,
                    "internalMarks" to internal,
                    "externalMarks" to external,
                    "grade" to gradeStr
                )
                firestore.collection("Grades").add(data)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Grade Updated", Toast.LENGTH_SHORT).show()
                        etSubGrade.text.clear()
                        etInternal.text.clear()
                        etExternal.text.clear()
                        etGradeValue.text.clear()
                    }
            }
        }
    }
}
