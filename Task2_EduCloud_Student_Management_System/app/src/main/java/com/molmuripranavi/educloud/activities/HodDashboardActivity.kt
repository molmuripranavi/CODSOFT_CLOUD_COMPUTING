package com.molmuripranavi.educloud.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.firestore.FirebaseFirestore
import com.molmuripranavi.educloud.R
import com.molmuripranavi.educloud.adapters.HodLeaveAdapter
import com.molmuripranavi.educloud.models.LeaveRequest

class HodDashboardActivity : AppCompatActivity() {

    private lateinit var recyclerHod: RecyclerView
    private lateinit var adapter: HodLeaveAdapter

    private val leaveList = ArrayList<LeaveRequest>()

    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hod_dashboard)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "HOD Dashboard"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        recyclerHod = findViewById(R.id.recyclerHod)

        recyclerHod.layoutManager = LinearLayoutManager(this)

        adapter = HodLeaveAdapter(leaveList)

        recyclerHod.adapter = adapter

        loadLeaves()
    }

    private fun loadLeaves() {

        firestore.collection("LeaveRequests")
            .orderBy("timestamp")
            .addSnapshotListener { value, error ->

                if (error != null) return@addSnapshotListener

                leaveList.clear()

                value?.documents?.forEach {

                    val leave =
                        it.toObject(LeaveRequest::class.java)

                    if (leave != null) {

                        leave.id = it.id

                        leaveList.add(leave)
                    }
                }

                leaveList.reverse()

                adapter.notifyDataSetChanged()
            }
    }
}