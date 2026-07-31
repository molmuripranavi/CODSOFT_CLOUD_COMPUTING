package com.molmuripranavi.educloud.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.molmuripranavi.educloud.R
import com.molmuripranavi.educloud.models.StudentProfile

class StudentListAdapter(
    private val studentList: List<StudentProfile>,
    private val onItemClick: (StudentProfile) -> Unit
) : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.txtStudentName)
        val txtRoll: TextView = itemView.findViewById(R.id.txtRollNumber)
        val txtDetails: TextView = itemView.findViewById(R.id.txtDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = studentList[position]
        holder.txtName.text = student.name
        holder.txtRoll.text = "Roll: ${student.rollNumber}"
        holder.txtDetails.text = "${student.department} | ${student.year} | Sec ${student.section}"

        holder.itemView.setOnClickListener {
            onItemClick(student)
        }
    }

    override fun getItemCount(): Int = studentList.size
}
