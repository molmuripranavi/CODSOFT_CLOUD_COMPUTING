package com.molmuripranavi.educloud.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.molmuripranavi.educloud.R
import com.molmuripranavi.educloud.adapter.LeaveHistoryAdapter
import com.molmuripranavi.educloud.models.LeaveRequest
import com.google.android.material.appbar.MaterialToolbar

class LeaveHistoryActivity : AppCompatActivity() {

    private lateinit var recyclerLeave: RecyclerView

    private lateinit var adapter: LeaveHistoryAdapter

    private val leaveList = ArrayList<LeaveRequest>()

    private lateinit var firestore: FirebaseFirestore

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leave_history)

        recyclerLeave = findViewById(R.id.recyclerLeave)

        recyclerLeave.layoutManager =
            LinearLayoutManager(this)

        adapter = LeaveHistoryAdapter(leaveList)

        recyclerLeave.adapter = adapter

        firestore = FirebaseFirestore.getInstance()

        auth = FirebaseAuth.getInstance()

        loadLeaveHistory()
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Leave History"

        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun loadLeaveHistory() {

        val email = auth.currentUser?.email ?: return

        firestore.collection("LeaveRequests")
            .whereEqualTo("email", email)
            .orderBy("timestamp")
            .addSnapshotListener { value, error ->

                if (error != null) {
                    return@addSnapshotListener
                }

                leaveList.clear()

                value?.documents?.forEach {

                    val leave =
                        it.toObject(LeaveRequest::class.java)

                    if (leave != null) {
                        leaveList.add(leave)
                    }
                }

                leaveList.reverse()

                adapter.notifyDataSetChanged()
            }
    }
}