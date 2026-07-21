package com.molmuripranavi.educloud.models

data class LeaveRequest(

    var id: String = "",

    var name: String = "",

    var email: String = "",

    var department: String = "",

    var year: String = "",

    var section: String = "",

    var leaveType: String = "",

    var fromDate: String = "",

    var toDate: String = "",

    var days: String = "",

    var reason: String = "",

    var status: String = "Pending",

    var fileUrl: String = "",

    var timestamp: Long = 0
)