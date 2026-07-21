package com.molmuripranavi.educloud.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.molmuripranavi.educloud.R
import com.molmuripranavi.educloud.models.LeaveRequest

class LeaveHistoryAdapter(
    private val list: List<LeaveRequest>
) : RecyclerView.Adapter<LeaveHistoryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

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
            .inflate(
                R.layout.item_leave,
                parent,
                false
            )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val leave = list[position]

        holder.txtLeaveType.text = leave.leaveType

        holder.txtDates.text =
            "${leave.fromDate}  →  ${leave.toDate}"

        holder.txtDays.text = leave.days + " Day(s)"

        holder.txtStatus.text = leave.status
    }

    override fun getItemCount(): Int {
        return list.size
    }
}