package com.molmuripranavi.educloud.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.molmuripranavi.educloud.R
import com.molmuripranavi.educloud.adapters.HodLeaveAdapter
import com.molmuripranavi.educloud.models.LeaveRequest
import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText

class HodLeaveListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HodLeaveAdapter
    private val leaveList = ArrayList<LeaveRequest>()
    private val db = FirebaseFirestore.getInstance()
    private var statusFilter: String? = null
    private lateinit var etSearch: TextInputEditText
    private val originalList = ArrayList<LeaveRequest>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hod_leave_list)

        statusFilter = intent.getStringExtra("STATUS_FILTER")

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        when (statusFilter) {
            "Pending" -> supportActionBar?.title = "Pending Requests"
            "Approved" -> supportActionBar?.title = "Approved Leaves"
            "Rejected" -> supportActionBar?.title = "Rejected Leaves"
            else -> supportActionBar?.title = "Leave Requests"
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = HodLeaveAdapter(leaveList)
        recyclerView.adapter = adapter

        loadLeaves()
        etSearch = findViewById(R.id.etSearch)

        etSearch.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {}

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                filterLeaves(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun loadLeaves() {
        var query: Query = db.collection("LeaveRequests")

        query = when (statusFilter) {
            "Pending" -> query.whereEqualTo("status", "Teacher Approved")
            "Approved" -> query.whereEqualTo("status", "Approved")
            "Rejected" -> query.whereEqualTo("status", "Rejected")
            else -> query
        }

        query.orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { value, error ->
                if (error != null) return@addSnapshotListener

                leaveList.clear()
                originalList.clear()
                value?.documents?.forEach { doc ->
                    val leave = doc.toObject(LeaveRequest::class.java)
                    if (leave != null) {
                        leave.id = doc.id
                        leaveList.add(leave)
                        originalList.add(leave)
                    }
                }
                adapter.notifyDataSetChanged()
            }
    }
    private fun filterLeaves(query: String) {

        leaveList.clear()

        if (query.isEmpty()) {

            leaveList.addAll(originalList)

        } else {

            val search = query.lowercase()

            for (leave in originalList) {

                if (leave.studentName.lowercase().contains(search) ||
                    leave.department.lowercase().contains(search)
                ) {

                    leaveList.add(leave)
                }
            }
        }

        adapter.notifyDataSetChanged()
    }
}
