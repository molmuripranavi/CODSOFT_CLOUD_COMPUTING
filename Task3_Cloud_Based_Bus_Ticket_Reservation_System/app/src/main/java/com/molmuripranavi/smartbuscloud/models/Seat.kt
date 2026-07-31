package com.molmuripranavi.smartbuscloud.models

data class Seat(
    var seatNumber: String = "",
    var isBooked: Boolean = false,
    var isSelected: Boolean = false
)
