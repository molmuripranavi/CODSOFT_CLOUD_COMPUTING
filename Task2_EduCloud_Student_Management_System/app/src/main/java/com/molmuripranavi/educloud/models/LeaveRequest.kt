package com.molmuripranavi.educloud.models

data class LeaveRequest(
    val name: String = "",
    val department: String = "",
    val year: String = "",
    val section: String = "",
    val reason: String = "",
    val status: String = "Pending",
    val email: String = ""
)