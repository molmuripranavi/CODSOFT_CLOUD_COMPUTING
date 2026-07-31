package com.molmuripranavi.educloud.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

import com.molmuripranavi.educloud.R
import com.molmuripranavi.educloud.models.StudentAttendance


class AttendanceAdapter(
    private val attendanceList: ArrayList<StudentAttendance>
) : RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder>() {


    class AttendanceViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {


        val txtSubjectName: TextView =
            itemView.findViewById(R.id.txtSubjectName)


        val txtPercentage: TextView =
            itemView.findViewById(R.id.txtPercentage)


        val txtClasses: TextView =
            itemView.findViewById(R.id.txttotalClasses)

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AttendanceViewHolder {


        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_attendance,
                parent,
                false
            )


        return AttendanceViewHolder(view)

    }


    override fun onBindViewHolder(
        holder: AttendanceViewHolder,
        position: Int
    ) {


        val attendance = attendanceList[position]


        holder.txtSubjectName.text =
            attendance.subjectName


        holder.txtPercentage.text =
            "Attendance: ${attendance.percentage}"


        holder.txtClasses.text =
            "${attendance.attendedClasses}/${attendance.totalClasses} Classes"

    }


    override fun getItemCount(): Int {

        return attendanceList.size

    }

}