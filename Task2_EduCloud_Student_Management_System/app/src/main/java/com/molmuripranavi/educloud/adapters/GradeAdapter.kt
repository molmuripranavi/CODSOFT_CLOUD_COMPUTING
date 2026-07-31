package com.molmuripranavi.educloud.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

import com.molmuripranavi.educloud.R
import com.molmuripranavi.educloud.models.StudentGrade


class GradeAdapter(
    private val gradeList: ArrayList<StudentGrade>
) : RecyclerView.Adapter<GradeAdapter.GradeViewHolder>() {


    class GradeViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {


        val txtSubject: TextView =
            itemView.findViewById(R.id.txtSubject)


        val txtInternal: TextView =
            itemView.findViewById(R.id.txtInternal)


        val txtExternal: TextView =
            itemView.findViewById(R.id.txtExternal)


        val txtGrade: TextView =
            itemView.findViewById(R.id.txtGrade)

    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GradeViewHolder {


        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_grade,
                parent,
                false
            )


        return GradeViewHolder(view)

    }



    override fun onBindViewHolder(
        holder: GradeViewHolder,
        position: Int
    ) {


        val grade =
            gradeList[position]


        holder.txtSubject.text =
            grade.subjectName


        holder.txtInternal.text =
            "Internal Marks : ${grade.internalMarks}"


        holder.txtExternal.text =
            "External Marks : ${grade.externalMarks}"


        holder.txtGrade.text =
            "Grade : ${grade.grade}"

    }



    override fun getItemCount(): Int {

        return gradeList.size

    }

}