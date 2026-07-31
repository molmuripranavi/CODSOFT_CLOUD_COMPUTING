package com.molmuripranavi.educloud.activities

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.molmuripranavi.educloud.R
import com.molmuripranavi.educloud.adapters.LeaveHistoryAdapter
import com.molmuripranavi.educloud.models.LeaveRequest

class StudentLeaveHistoryActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var txtEmpty: TextView
    private lateinit var adapter: LeaveHistoryAdapter

    private val leaveList = ArrayList<LeaveRequest>()

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_leave_history)

        // Toolbar
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "My Leave History"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        // Views
        recyclerView = findViewById(R.id.recyclerHistory)
        txtEmpty = findViewById(R.id.txtEmpty)

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = LeaveHistoryAdapter(leaveList)
        recyclerView.adapter = adapter

        loadLeaveHistory()
    }

    private fun loadLeaveHistory() {

        val email = auth.currentUser?.email ?: return

        firestore.collection("LeaveRequests")
            .whereEqualTo("email", email)
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { value, error ->

                if (error != null) return@addSnapshotListener

                leaveList.clear()

                value?.documents?.forEach { document ->

                    val leave =
                        document.toObject(LeaveRequest::class.java)

                    if (leave != null) {
                        leave.id = document.id
                        leaveList.add(leave)
                    }
                }

                adapter.notifyDataSetChanged()

                if (leaveList.isEmpty()) {
                    txtEmpty.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                } else {
                    txtEmpty.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                }
            }
    }
}