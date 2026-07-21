package com.molmuripranavi.educloud.activities

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ProgressBar
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.molmuripranavi.educloud.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ApplyLeaveActivity : AppCompatActivity() {

    // Firebase
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var storage: FirebaseStorage

    // Text Fields
    private lateinit var etName: TextInputEditText
    private lateinit var etFromDate: TextInputEditText
    private lateinit var etToDate: TextInputEditText
    private lateinit var etDays: TextInputEditText
    private lateinit var etReason: TextInputEditText

    // Dropdowns
    private lateinit var actDepartment: AutoCompleteTextView
    private lateinit var actYear: AutoCompleteTextView
    private lateinit var actSection: AutoCompleteTextView
    private lateinit var actLeaveType: AutoCompleteTextView

    // TextViews
    private lateinit var txtEmail: TextView
    private lateinit var txtSelectedFile: TextView

    // Buttons
    private lateinit var btnUpload: MaterialButton
    private lateinit var btnSubmit: MaterialButton
    private lateinit var btnReset: MaterialButton

    // ProgressBar
    private lateinit var progressBar: ProgressBar

    // RadioGroup
    private lateinit var rgStudentType: RadioGroup

    // File
    private var fileUri: Uri? = null
    private var uploadedFileUrl = ""

    companion object {
        const val PICK_FILE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_leave)

        // Firebase
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance()

        // Text Fields
        etName = findViewById(R.id.etName)
        etFromDate = findViewById(R.id.etFromDate)
        etToDate = findViewById(R.id.etToDate)
        etDays = findViewById(R.id.etDays)
        etReason = findViewById(R.id.etReason)

        // Dropdowns
        actDepartment = findViewById(R.id.actDepartment)
        actYear = findViewById(R.id.actYear)
        actSection = findViewById(R.id.actSection)
        actLeaveType = findViewById(R.id.actLeaveType)

        // TextViews
        txtEmail = findViewById(R.id.txtEmail)
        txtSelectedFile = findViewById(R.id.txtSelectedFile)

        // Buttons
        btnUpload = findViewById(R.id.btnUpload)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnReset = findViewById(R.id.btnReset)

        // ProgressBar
        progressBar = findViewById(R.id.progressBar)

        // RadioGroup
        rgStudentType = findViewById(R.id.rgStudentType)

        // Logged in Email
        txtEmail.text = auth.currentUser?.email ?: "No Email"

        // ===== Remaining code will be added in Part 2 =====
        // =======================
// Department Dropdown
// =======================

        val departments = arrayOf(
            "CSE",
            "CSE (AI & ML)",
            "CSE (Data Science)",
            "CSE (Cyber Security)",
            "IT",
            "ECE",
            "EEE",
            "Mechanical",
            "Civil"
        )

        val departmentAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            departments
        )

        actDepartment.setAdapter(departmentAdapter)


// =======================
// Year Dropdown
// =======================

        val years = arrayOf(
            "1st Year",
            "2nd Year",
            "3rd Year",
            "4th Year"
        )

        val yearAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            years
        )

        actYear.setAdapter(yearAdapter)


// =======================
// Section Dropdown
// =======================

        val sections = arrayOf(
            "A",
            "B",
            "C",
            "D",
            "E",
            "F"
        )

        val sectionAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            sections
        )

        actSection.setAdapter(sectionAdapter)


// =======================
// Leave Type Dropdown
// =======================

        val leaveTypes = arrayOf(
            "Sick Leave",
            "Medical Leave",
            "Casual Leave",
            "Emergency Leave",
            "Personal Leave",
            "On Duty"
        )

        val leaveTypeAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            leaveTypes
        )

        actLeaveType.setAdapter(leaveTypeAdapter)


// =======================
// Calendar
// =======================

        val calendar = Calendar.getInstance()

        val dateFormatter = SimpleDateFormat(
            "dd/MM/yyyy",
            Locale.getDefault()
        )


// =======================
// From Date
// =======================

        etFromDate.setOnClickListener {

            DatePickerDialog(
                this,
                { _, year, month, day ->

                    calendar.set(year, month, day)

                    etFromDate.setText(
                        dateFormatter.format(calendar.time)
                    )

                    calculateLeaveDays()

                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)

            ).show()

        }


// =======================
// To Date
// =======================

        etToDate.setOnClickListener {

            DatePickerDialog(
                this,
                { _, year, month, day ->

                    calendar.set(year, month, day)

                    etToDate.setText(
                        dateFormatter.format(calendar.time)
                    )

                    calculateLeaveDays()

                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)

            ).show()

        }


// =======================
// Reset Button
// =======================

        btnReset.setOnClickListener {

            etName.text?.clear()

            actDepartment.setText("", false)
            actYear.setText("", false)
            actSection.setText("", false)
            actLeaveType.setText("", false)

            etFromDate.text?.clear()
            etToDate.text?.clear()
            etDays.text?.clear()
            etReason.text?.clear()

            rgStudentType.clearCheck()

            txtSelectedFile.text = "No file selected"

            fileUri = null

            uploadedFileUrl = ""

            Toast.makeText(
                this,
                "Form Reset Successfully",
                Toast.LENGTH_SHORT
            ).show()
        }
        // =======================
// Upload Button
// =======================

        btnUpload.setOnClickListener {

            val intent = Intent(Intent.ACTION_GET_CONTENT)

            intent.type = "*/*"

            intent.putExtra(
                Intent.EXTRA_MIME_TYPES,
                arrayOf(
                    "application/pdf",
                    "image/jpeg",
                    "image/png"
                )
            )

            startActivityForResult(
                Intent.createChooser(
                    intent,
                    "Select Medical Certificate"
                ),
                PICK_FILE
            )
        }
        // =======================
// Submit Leave Request
// =======================

        btnSubmit.setOnClickListener {

            val name = etName.text.toString().trim()
            val department = actDepartment.text.toString().trim()
            val year = actYear.text.toString().trim()
            val section = actSection.text.toString().trim()
            val leaveType = actLeaveType.text.toString().trim()
            val fromDate = etFromDate.text.toString().trim()
            val toDate = etToDate.text.toString().trim()
            val totalDays = etDays.text.toString().trim()
            val reason = etReason.text.toString().trim()

            val studentType = when (rgStudentType.checkedRadioButtonId) {
                R.id.rbDayScholar -> "Day Scholar"
                R.id.rbHosteller -> "Hosteller"
                else -> ""
            }

            // Validation
            if (
                name.isEmpty() ||
                department.isEmpty() ||
                year.isEmpty() ||
                section.isEmpty() ||
                studentType.isEmpty() ||
                leaveType.isEmpty() ||
                fromDate.isEmpty() ||
                toDate.isEmpty() ||
                totalDays.isEmpty() ||
                reason.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Please fill all required fields",
                    Toast.LENGTH_LONG
                ).show()

                return@setOnClickListener
            }

            progressBar.visibility = android.view.View.VISIBLE

            val leaveRequest = hashMapOf(

                "studentName" to name,
                "email" to (auth.currentUser?.email ?: ""),

                "department" to department,
                "year" to year,
                "section" to section,

                "studentType" to studentType,
                "leaveType" to leaveType,

                "fromDate" to fromDate,
                "toDate" to toDate,
                "totalDays" to totalDays,

                "reason" to reason,

                "certificateUrl" to uploadedFileUrl,

                "status" to "Pending",

                "timestamp" to System.currentTimeMillis()

            )

            firestore.collection("LeaveRequests")

                .add(leaveRequest)

                .addOnSuccessListener {

                    progressBar.visibility = android.view.View.GONE

                    Toast.makeText(
                        this,
                        "Leave Request Submitted Successfully",
                        Toast.LENGTH_LONG
                    ).show()

                    clearForm()

                }

                .addOnFailureListener {

                    progressBar.visibility = android.view.View.GONE

                    Toast.makeText(
                        this,
                        "Submission Failed",
                        Toast.LENGTH_LONG
                    ).show()

                }

        }

    }
    private fun calculateLeaveDays() {

        if (
            etFromDate.text.isNullOrEmpty() ||
            etToDate.text.isNullOrEmpty()
        ) {
            return
        }

        try {

            val sdf = SimpleDateFormat(
                "dd/MM/yyyy",
                Locale.getDefault()
            )

            val fromDate =
                sdf.parse(etFromDate.text.toString())

            val toDate =
                sdf.parse(etToDate.text.toString())


            if (fromDate != null && toDate != null) {

                val difference =
                    toDate.time - fromDate.time


                val days =
                    (difference /
                            (1000 * 60 * 60 * 24)) + 1


                if (days > 0) {

                    etDays.setText(
                        days.toString()
                    )

                } else {

                    Toast.makeText(
                        this,
                        "To Date should be after From Date",
                        Toast.LENGTH_SHORT
                    ).show()

                    etDays.text?.clear()
                }
            }

        } catch (e: Exception) {

            e.printStackTrace()

        }
    }
    private fun clearForm() {

        etName.text?.clear()

        actDepartment.setText("", false)
        actYear.setText("", false)
        actSection.setText("", false)
        actLeaveType.setText("", false)


        etFromDate.text?.clear()
        etToDate.text?.clear()
        etDays.text?.clear()

        etReason.text?.clear()


        rgStudentType.clearCheck()


        txtSelectedFile.text =
            "No file selected"


        fileUri = null

        uploadedFileUrl = ""

    }
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        if (
            requestCode == PICK_FILE &&
            resultCode == RESULT_OK &&
            data != null &&
            data.data != null
        ) {

            fileUri = data.data

            txtSelectedFile.text =
                fileUri?.lastPathSegment ?: "File Selected"

            uploadFileToFirebase()
        }
    }
    private fun uploadFileToFirebase() {

        if (fileUri == null)
            return

        progressBar.visibility = android.view.View.VISIBLE

        val fileName =
            "leave_documents/" +
                    System.currentTimeMillis()

        val reference =
            storage.reference.child(fileName)

        reference.putFile(fileUri!!)
            .addOnSuccessListener {

                reference.downloadUrl
                    .addOnSuccessListener { uri ->

                        uploadedFileUrl = uri.toString()

                        progressBar.visibility =
                            android.view.View.GONE

                        Toast.makeText(
                            this,
                            "File Uploaded Successfully",
                            Toast.LENGTH_SHORT
                        ).show()

                    }

            }
            .addOnFailureListener {

                progressBar.visibility =
                    android.view.View.GONE

                Toast.makeText(
                    this,
                    "Upload Failed",
                    Toast.LENGTH_SHORT
                ).show()

            }
    }

}