package com.molmuripranavi.smartbuscloud.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.molmuripranavi.smartbuscloud.R
import com.molmuripranavi.smartbuscloud.activities.passenger.SeatSelectionActivity
import com.molmuripranavi.smartbuscloud.models.Bus
import java.text.SimpleDateFormat
import java.util.Locale

class BusAdapter(
    private val context: Context,
    private val busList: ArrayList<Bus>,
    private val journeyDate: String
) : RecyclerView.Adapter<BusAdapter.BusViewHolder>() {

    inner class BusViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val txtBusName: TextView =
            itemView.findViewById(R.id.txtBusName)

        val txtBusType: TextView =
            itemView.findViewById(R.id.txtBusType)

        val txtBoarding: TextView =
            itemView.findViewById(R.id.txtBoarding)

        val txtRoute: TextView =
            itemView.findViewById(R.id.txtRoute)

        val txtTime: TextView =
            itemView.findViewById(R.id.txtTime)

        val txtDuration: TextView =
            itemView.findViewById(R.id.txtDuration)

        val txtFare: TextView =
            itemView.findViewById(R.id.txtFare)

        val txtSeats: TextView =
            itemView.findViewById(R.id.txtSeats)

        val btnBook: MaterialButton =
            itemView.findViewById(R.id.btnBook)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BusViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bus, parent, false)

        return BusViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: BusViewHolder,
        position: Int
    ) {

        val bus = busList[position]

        // Bus Name
        holder.txtBusName.text = bus.busName

        // Bus Type
        holder.txtBusType.text = "⭐ ${bus.busType}"

        // Boarding Time
        holder.txtBoarding.text = "🕒 Boarding : ${bus.departure}"

        // Route
        holder.txtRoute.text = "${bus.from} → ${bus.to}"

        // Departure & Arrival
        holder.txtTime.text = "${bus.departure} → ${bus.arrival}"

        // Duration
        holder.txtDuration.text =
            "⏱ ${calculateDuration(bus.departure, bus.arrival)}"

        // Fare
        holder.txtFare.text = "₹${bus.fare}"

        // Seats
        holder.txtSeats.text =
            "${bus.availableSeats} Seats Available"

        holder.btnBook.setOnClickListener {

            val intent = Intent(
                context,
                SeatSelectionActivity::class.java
            )

            intent.putExtra("busId", bus.id)
            intent.putExtra("busName", bus.busName)
            intent.putExtra("busType", bus.busType)
            intent.putExtra("from", bus.from)
            intent.putExtra("to", bus.to)
            intent.putExtra("departure", bus.departure)
            intent.putExtra("arrival", bus.arrival)
            intent.putExtra("fare", bus.fare)
            intent.putExtra("availableSeats", bus.availableSeats)
            intent.putExtra("date", journeyDate)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return busList.size
    }

    /**
     * Calculates journey duration.
     * Works with time format like:
     * 02:30
     * 14:45
     */

    private fun calculateDuration(
        departure: String,
        arrival: String
    ): String {

        return try {

            val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())

            val start = sdf.parse(departure)
            val end = sdf.parse(arrival)

            if (start != null && end != null) {

                var diff = end.time - start.time

                if (diff < 0) {
                    diff += 24 * 60 * 60 * 1000
                }

                val hours = diff / (1000 * 60 * 60)
                val minutes = (diff / (1000 * 60)) % 60

                "${hours}h ${minutes}m Journey"

            } else {

                "Comfortable Journey"
            }

        } catch (e: Exception) {

            "Comfortable Journey"
        }
    }
}