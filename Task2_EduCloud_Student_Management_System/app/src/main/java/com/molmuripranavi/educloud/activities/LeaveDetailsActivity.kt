package com.molmuripranavi.educloud.activities

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.chip.Chip
import com.molmuripranavi.educloud.R
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.google.android.material.button.MaterialButton

class LeaveDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leave_details)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        findViewById<TextView>(R.id.txtStudentName).text = intent.getStringExtra("studentName")
        findViewById<TextView>(R.id.txtEmail).text = intent.getStringExtra("email")
        findViewById<TextView>(R.id.txtDepartment).text = intent.getStringExtra("department")
        findViewById<TextView>(R.id.txtYear).text = intent.getStringExtra("year")
        findViewById<TextView>(R.id.txtSection).text = intent.getStringExtra("section")
        findViewById<TextView>(R.id.txtStudentType).text = intent.getStringExtra("studentType")
        findViewById<TextView>(R.id.txtLeaveType).text = intent.getStringExtra("leaveType")
        findViewById<TextView>(R.id.txtFromDate).text = intent.getStringExtra("fromDate")
        findViewById<TextView>(R.id.txtToDate).text = intent.getStringExtra("toDate")
        findViewById<TextView>(R.id.txtDays).text = intent.getStringExtra("totalDays")
        findViewById<TextView>(R.id.txtReason).text = intent.getStringExtra("reason")

        val chipStatus = findViewById<Chip>(R.id.chipStatus)
        val btnViewCertificate =
            findViewById<MaterialButton>(R.id.btnViewCertificate)

        val certificateUrl =
            intent.getStringExtra("certificateUrl")

        if (certificateUrl.isNullOrEmpty()) {

            btnViewCertificate.isEnabled = false
            btnViewCertificate.text = "No Certificate Uploaded"

        } else {

            btnViewCertificate.setOnClickListener {

                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(certificateUrl)
                )

                try {
                    startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(
                        this,
                        "Unable to open certificate",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        val status = intent.getStringExtra("status") ?: "Pending"

        chipStatus.text = status

        when (status) {
            "Approved" -> {
                chipStatus.chipBackgroundColor =
                    ColorStateList.valueOf(Color.parseColor("#4CAF50"))
            }

            "Rejected" -> {
                chipStatus.chipBackgroundColor =
                    ColorStateList.valueOf(Color.parseColor("#F44336"))
            }

            "Teacher Approved" -> {
                chipStatus.chipBackgroundColor =
                    ColorStateList.valueOf(Color.parseColor("#2196F3"))
            }

            else -> {
                chipStatus.chipBackgroundColor =
                    ColorStateList.valueOf(Color.parseColor("#FF9800"))
            }
        }
    }
}