package com.molmuripranavi.educloud.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.molmuripranavi.educloud.R
import com.molmuripranavi.educloud.models.LeaveRequest
import com.google.firebase.firestore.FirebaseFirestore
import android.content.Intent
import android.net.Uri

class HodLeaveAdapter(
    private val leaveList: ArrayList<LeaveRequest>
) : RecyclerView.Adapter<HodLeaveAdapter.ViewHolder>() {
    private val firestore = FirebaseFirestore.getInstance()


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtStudentName: TextView =
            itemView.findViewById(R.id.txtStudentName)

        val txtDepartment: TextView =
            itemView.findViewById(R.id.txtDepartment)

        val txtLeaveType: TextView =
            itemView.findViewById(R.id.txtLeaveType)

        val txtDates: TextView =
            itemView.findViewById(R.id.txtDates)

        val txtReason: TextView =
            itemView.findViewById(R.id.txtReason)

        val btnApprove: MaterialButton =
            itemView.findViewById(R.id.btnApprove)

        val btnReject: MaterialButton =
            itemView.findViewById(R.id.btnReject)
        val btnViewCertificate: MaterialButton =
            itemView.findViewById(R.id.btnViewCertificate)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_hod_leave,
                parent,
                false
            )

        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return leaveList.size
    }


    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val leave = leaveList[position]


        // Student details

        holder.txtStudentName.text =
            leave.studentName


        holder.txtDepartment.text =
            "${leave.department} | ${leave.year} | ${leave.section}"


        holder.txtLeaveType.text =
            "Leave : ${leave.leaveType}"


        holder.txtDates.text =
            "${leave.fromDate} → ${leave.toDate}"


        holder.txtReason.text =
            "Reason : ${leave.reason}"
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



        // Default pending status

        when (leave.status) {


            "Approved" -> {

                holder.btnApprove.text = "Approved"
                holder.btnApprove.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#4CAF50"))
                holder.btnApprove.setTextColor(Color.WHITE)
                holder.btnApprove.isEnabled = false
                holder.btnReject.visibility = View.GONE
            }



            "Rejected" -> {

                holder.btnReject.text = "Rejected"
                holder.btnReject.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#F44336"))
                holder.btnReject.setTextColor(Color.WHITE)
                holder.btnReject.isEnabled = false
                holder.btnApprove.visibility = View.GONE
            }



            else -> {

                // Pending

                holder.btnApprove.text = "Approve"
                holder.btnApprove.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#1565C0"))
                holder.btnApprove.setTextColor(Color.WHITE)
                holder.btnApprove.visibility = View.VISIBLE

                holder.btnReject.text = "Reject"
                holder.btnReject.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#F44336"))
                holder.btnReject.setTextColor(Color.WHITE)
                holder.btnReject.visibility = View.VISIBLE

                holder.btnApprove.isEnabled = true
                holder.btnReject.isEnabled = true


                // HOD Approve

                holder.btnApprove.setOnClickListener {

                    firestore.collection("LeaveRequests")
                        .document(leave.id)
                        .update("status", "Approved")
                }



                // HOD Reject

                holder.btnReject.setOnClickListener {

                    firestore.collection("LeaveRequests")
                        .document(leave.id)
                        .update("status", "Rejected")
                }

            }

        }

    }
}
