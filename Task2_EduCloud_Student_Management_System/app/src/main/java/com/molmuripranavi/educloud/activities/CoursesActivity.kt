package com.molmuripranavi.educloud.activities

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.firestore.FirebaseFirestore
import com.molmuripranavi.educloud.R
import com.molmuripranavi.educloud.adapters.CourseAdapter
import com.molmuripranavi.educloud.models.Course


class CoursesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CourseAdapter
    private lateinit var firestore: FirebaseFirestore
    private lateinit var txtEmpty: TextView

    private val courseList = ArrayList<Course>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Remove manual overrides - rely on Theme for professional blue status bar
        setContentView(R.layout.activity_courses)

        // Toolbar
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        if (toolbar != null) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            toolbar.setNavigationOnClickListener { finish() }
        }

        // Views
        recyclerView = findViewById(R.id.courseRecyclerView)
        txtEmpty = findViewById(R.id.txtEmpty)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CourseAdapter(courseList)
        recyclerView.adapter = adapter

        firestore = FirebaseFirestore.getInstance()
        loadCourses()
    }

    private fun loadCourses() {
        firestore.collection("Courses")
            .get()
            .addOnSuccessListener { result ->
                courseList.clear()
                for (document in result) {
                    val course = document.toObject(Course::class.java)
                    course.id = document.id
                    courseList.add(course)
                }
                adapter.notifyDataSetChanged()

                if (courseList.isEmpty()) {
                    recyclerView.visibility = View.GONE
                    txtEmpty.visibility = View.VISIBLE
                } else {
                    recyclerView.visibility = View.VISIBLE
                    txtEmpty.visibility = View.GONE
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Unable to load courses", Toast.LENGTH_SHORT).show()
            }
    }
}
