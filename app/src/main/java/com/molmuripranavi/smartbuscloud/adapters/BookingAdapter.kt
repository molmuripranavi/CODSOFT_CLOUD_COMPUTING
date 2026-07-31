package com.molmuripranavi.smartbuscloud.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.molmuripranavi.smartbuscloud.R
import com.molmuripranavi.smartbuscloud.models.Booking

class BookingAdapter(
    private val bookingList: ArrayList<Booking>,
) : RecyclerView.Adapter<BookingAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val txtBusName: TextView = itemView.findViewById(R.id.txtBusName)
        val txtRoute: TextView = itemView.findViewById(R.id.txtRoute)
        val txtSeat: TextView = itemView.findViewById(R.id.txtSeat)
        val txtFare: TextView = itemView.findViewById(R.id.txtFare)
        val txtStatus: TextView = itemView.findViewById(R.id.txtStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_booking, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val booking = bookingList[position]

        holder.txtBusName.text = booking.busName
        holder.txtRoute.text = holder.itemView.context.getString(R.string.route_format, booking.from, booking.to)
        holder.txtSeat.text = holder.itemView.context.getString(R.string.seat_format, booking.seatNumber)
        holder.txtFare.text = holder.itemView.context.getString(R.string.fare_format, booking.fare)
        holder.txtStatus.text = booking.status
    }

    override fun getItemCount(): Int = bookingList.size
}