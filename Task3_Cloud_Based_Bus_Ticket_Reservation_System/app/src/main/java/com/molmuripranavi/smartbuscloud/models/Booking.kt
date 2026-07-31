package com.molmuripranavi.smartbuscloud.models

data class Booking(

    var id: String = "",

    var userId: String = "",

    var passengerName: String = "",

    var passengerEmail: String = "",

    var busId: String = "",

    var busName: String = "",

    var from: String = "",

    var to: String = "",

    var seatNumber: String = "",

    var fare: Long = 0,

    var status: String = "",

    var bookingTime: Long = 0
)