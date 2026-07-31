package com.molmuripranavi.educloud.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.firebase.firestore.FirebaseFirestore
import com.molmuripranavi.educloud.R
import com.molmuripranavi.educloud.models.LeaveRequest
import android.content.Intent
import android.net.Uri


class TeacherLeaveAdapter(
    private val leaveList: ArrayList<LeaveRequest>
) : RecyclerView.Adapter<TeacherLeaveAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val name: TextView = itemView.findViewById(R.id.studentName)
        val department: TextView = itemView.findViewById(R.id.department)
        val dates: TextView = itemView.findViewById(R.id.dates)
        val reason: TextView = itemView.findViewById(R.id.reason)

        val approve: MaterialButton =
            itemView.findViewById(R.id.approveBtn)

        val reject: MaterialButton =
            itemView.findViewById(R.id.rejectBtn)
        val btnViewCertificate: MaterialButton =
            itemView.findViewById(R.id.btnViewCertificate)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_teacher_leave,parent,false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val leave = leaveList[position]


        holder.name.text = leave.studentName
        holder.department.text =
            "${leave.department} | ${leave.year} | ${leave.section}"

        holder.dates.text =
            "${leave.fromDate} - ${leave.toDate}"

        holder.reason.text = leave.reason
        holder.btnViewCertificate.setOnClickListener {

            if (leave.certificateUrl.isNotEmpty()) {

                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(leave.certificateUrl)
                )

                holder.itemView.context.startActivity(intent)

            } else {

                android.widget.Toast.makeText(
                    holder.itemView.context,
                    "No certificate uploaded",
                    android.widget.Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Handle Status UI
        when (leave.status) {
            "Teacher Approved", "Approved" -> {
                holder.approve.text = "Approved"
                holder.approve.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#4CAF50"))
                holder.approve.setTextColor(Color.WHITE)
                holder.approve.isEnabled = false
                holder.approve.visibility = View.VISIBLE
                holder.reject.visibility = View.GONE
            }
            "Rejected" -> {
                holder.reject.text = "Rejected"
                holder.reject.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#F44336"))
                holder.reject.setTextColor(Color.WHITE)
                holder.reject.isEnabled = false
                holder.reject.visibility = View.VISIBLE
                holder.approve.visibility = View.GONE
            }
            else -> {
                // Pending
                holder.approve.text = "Approve"
                holder.approve.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#1565C0"))
                holder.approve.setTextColor(Color.WHITE)
                holder.approve.isEnabled = true
                holder.approve.visibility = View.VISIBLE

                holder.reject.text = "Reject"
                holder.reject.backgroundTintList = null // Use default for TextButton or standard
                holder.reject.setTextColor(Color.parseColor("#F44336"))
                holder.reject.isEnabled = true
                holder.reject.visibility = View.VISIBLE

                holder.approve.setOnClickListener {
                    FirebaseFirestore.getInstance()
                        .collection("LeaveRequests")
                        .document(leave.id)
                        .update("status", "Teacher Approved")
                }

                holder.reject.setOnClickListener {
                    FirebaseFirestore.getInstance()
                        .collection("LeaveRequests")
                        .document(leave.id)
                        .update("status", "Rejected")
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return leaveList.size
    }
}
