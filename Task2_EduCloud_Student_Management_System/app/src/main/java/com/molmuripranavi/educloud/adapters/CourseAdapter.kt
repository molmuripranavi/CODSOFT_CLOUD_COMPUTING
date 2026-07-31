package com.molmuripranavi.educloud.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.molmuripranavi.educloud.R
import com.molmuripranavi.educloud.models.Course

class CourseAdapter(
    private val courseList: ArrayList<Course>
) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    class CourseViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val txtCourseName: TextView =
            itemView.findViewById(R.id.txtCourseName)

        val txtFaculty: TextView =
            itemView.findViewById(R.id.txtFaculty)

        val txtCredits: TextView =
            itemView.findViewById(R.id.txtCredits)

        val txtSemester: TextView =
            itemView.findViewById(R.id.txtSemester)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_course, parent, false)

        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CourseViewHolder,
        position: Int
    ) {

        val course = courseList[position]

        holder.txtCourseName.text =
            course.courseName

        holder.txtFaculty.text =
            "Faculty: ${course.faculty}"

        holder.txtCredits.text =
            "Credits: ${course.credits}"

        holder.txtSemester.text =
            "Semester: ${course.semester}"
    }

    override fun getItemCount(): Int {
        return courseList.size
    }
}