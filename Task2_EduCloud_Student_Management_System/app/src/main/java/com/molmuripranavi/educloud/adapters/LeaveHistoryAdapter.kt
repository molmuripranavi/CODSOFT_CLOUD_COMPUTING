package com.molmuripranavi.educloud.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.molmuripranavi.educloud.R
import com.molmuripranavi.educloud.models.LeaveRequest
import android.content.Intent

class LeaveHistoryAdapter(
    private val list: ArrayList<LeaveRequest>
) : RecyclerView.Adapter<LeaveHistoryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtLeaveType: TextView =
            itemView.findViewById(R.id.txtLeaveType)

        val txtDates: TextView =
            itemView.findViewById(R.id.txtDates)

        val txtDays: TextView =
            itemView.findViewById(R.id.txtDays)

        val txtStatus: TextView =
            itemView.findViewById(R.id.txtStatus)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_leave, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val leave = list[position]

        holder.txtLeaveType.text = leave.leaveType

        holder.txtDates.text =
            "${leave.fromDate} → ${leave.toDate}"

        holder.txtDays.text =
            "${leave.totalDays} Day(s)"

        holder.txtStatus.text = leave.status

        when (leave.status) {
            "Pending" ->
                holder.txtStatus.setTextColor(Color.parseColor("#FF9800"))

            "Teacher Approved" ->
                holder.txtStatus.setTextColor(Color.parseColor("#1565C0"))

            "Approved" ->
                holder.txtStatus.setTextColor(Color.parseColor("#4CAF50"))

            "Rejected" ->
                holder.txtStatus.setTextColor(Color.parseColor("#F44336"))
        }

        holder.itemView.setOnClickListener {

            val intent = android.content.Intent(
                holder.itemView.context,
                com.molmuripranavi.educloud.activities.LeaveDetailsActivity::class.java
            )

            intent.putExtra("studentName", leave.studentName)
            intent.putExtra("email", leave.email)
            intent.putExtra("department", leave.department)
            intent.putExtra("year", leave.year)
            intent.putExtra("section", leave.section)
            intent.putExtra("studentType", leave.studentType)
            intent.putExtra("leaveType", leave.leaveType)
            intent.putExtra("fromDate", leave.fromDate)
            intent.putExtra("toDate", leave.toDate)
            intent.putExtra("totalDays", leave.totalDays)
            intent.putExtra("reason", leave.reason)
            intent.putExtra("status", leave.status)
            intent.putExtra("certificateUrl", leave.certificateUrl)

            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}