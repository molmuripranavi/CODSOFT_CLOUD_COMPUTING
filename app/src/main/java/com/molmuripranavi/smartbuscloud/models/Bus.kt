package com.molmuripranavi.smartbuscloud.models

data class Bus(

    var id: String = "",

    var busName: String = "",

    var from: String = "",

    var to: String = "",

    var departure: String = "",

    var arrival: String = "",

    var fare: Long = 0L,

    var availableSeats: Long = 0L,

    var busType: String = ""

)