package com.molmuripranavi.educloud.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

import com.molmuripranavi.educloud.R
import com.molmuripranavi.educloud.models.Announcement


class AnnouncementAdapter(
    private val announcementList: ArrayList<Announcement>
) : RecyclerView.Adapter<AnnouncementAdapter.AnnouncementViewHolder>() {


    class AnnouncementViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {


        val txtTitle: TextView =
            itemView.findViewById(R.id.txtTitle)


        val txtMessage: TextView =
            itemView.findViewById(R.id.txtMessage)


        val txtPostedBy: TextView =
            itemView.findViewById(R.id.txtPostedBy)


        val txtDate: TextView =
            itemView.findViewById(R.id.txtDate)

    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnnouncementViewHolder {


        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_announcement,
                parent,
                false
            )


        return AnnouncementViewHolder(view)

    }



    override fun onBindViewHolder(
        holder: AnnouncementViewHolder,
        position: Int
    ) {


        val announcement =
            announcementList[position]


        holder.txtTitle.text =
            announcement.title


        holder.txtMessage.text =
            announcement.message


        holder.txtPostedBy.text =
            "Posted By: ${announcement.postedBy}"


        holder.txtDate.text =
            "Date: ${announcement.date}"

    }



    override fun getItemCount(): Int {

        return announcementList.size

    }

}