package com.molmuripranavi.educloud.models

data class LeaveRequest(
    var id: String = "",
    var studentName: String = "",
    var email: String = "",
    var department: String = "",
    var year: String = "",
    var section: String = "",
    var studentType: String = "",
    var leaveType: String = "",
    var fromDate: String = "",
    var toDate: String = "",
    var totalDays: String = "",
    var reason: String = "",
    var status: String = "Pending",
    var certificateUrl: String = "",
    var timestamp: Long = 0
)
