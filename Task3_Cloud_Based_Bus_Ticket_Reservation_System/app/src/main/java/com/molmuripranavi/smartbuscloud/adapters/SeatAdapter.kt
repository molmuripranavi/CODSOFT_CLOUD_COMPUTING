package com.molmuripranavi.smartbuscloud.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.molmuripranavi.smartbuscloud.R
import com.molmuripranavi.smartbuscloud.models.Seat

class SeatAdapter(
    private val seatList: ArrayList<Seat>,
    private val onSeatSelected: (Seat) -> Unit
) : RecyclerView.Adapter<SeatAdapter.SeatViewHolder>() {

    class SeatViewHolder(itemView: MaterialCardView) :
        RecyclerView.ViewHolder(itemView) {

        val cardSeat: MaterialCardView = itemView

        val txtSeat: TextView =
            itemView.findViewById(R.id.txtSeat)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SeatViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_seat,
                parent,
                false
            ) as MaterialCardView

        return SeatViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: SeatViewHolder,
        position: Int
    ) {

        val seat = seatList[position]

        holder.txtSeat.text = seat.seatNumber

        if (seat.isBooked) {

            holder.cardSeat.setCardBackgroundColor(
                Color.parseColor("#E53935")
            )

            holder.cardSeat.isClickable = false

        } else if (seat.isSelected) {

            holder.cardSeat.setCardBackgroundColor(
                Color.parseColor("#1565C0")
            )

            holder.cardSeat.isClickable = true

        } else {

            holder.cardSeat.setCardBackgroundColor(
                Color.parseColor("#43A047")
            )

            holder.cardSeat.isClickable = true
        }

        holder.txtSeat.setTextColor(Color.WHITE)

        holder.itemView.setOnClickListener {

            if (seat.isBooked)
                return@setOnClickListener

            // Toggle selection
            seat.isSelected = !seat.isSelected

            notifyItemChanged(position)

            onSeatSelected(seat)
        }
    }

    override fun getItemCount(): Int {
        return seatList.size
    }
}