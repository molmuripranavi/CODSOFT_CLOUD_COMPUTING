package com.molmuripranavi.educloud.activities

import android.os.Bundle
import android.widget.Toast

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.firebase.firestore.FirebaseFirestore

import com.molmuripranavi.educloud.R
import com.molmuripranavi.educloud.adapters.AnnouncementAdapter
import com.molmuripranavi.educloud.models.Announcement


class AnnouncementsActivity : AppCompatActivity() {


    private lateinit var announcementRecyclerView: RecyclerView

    private lateinit var announcementAdapter: AnnouncementAdapter

    private lateinit var firestore: FirebaseFirestore


    private val announcementList = ArrayList<Announcement>()



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_announcements)



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->

            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }



        announcementRecyclerView =
            findViewById(R.id.announcementRecyclerView)



        announcementRecyclerView.layoutManager =
            LinearLayoutManager(this)



        announcementAdapter =
            AnnouncementAdapter(announcementList)



        announcementRecyclerView.adapter =
            announcementAdapter



        firestore =
            FirebaseFirestore.getInstance()



        loadAnnouncements()

    }



    private fun loadAnnouncements() {


        firestore.collection("Announcements")

            .get()

            .addOnSuccessListener { documents ->


                announcementList.clear()


                for(document in documents) {


                    val announcement =
                        document.toObject(
                            Announcement::class.java
                        )


                    announcement.id =
                        document.id



                    announcementList.add(announcement)

                }



                announcementAdapter.notifyDataSetChanged()


            }


            .addOnFailureListener {


                Toast.makeText(
                    this,
                    "Failed to load announcements",
                    Toast.LENGTH_SHORT
                ).show()

            }


    }

}