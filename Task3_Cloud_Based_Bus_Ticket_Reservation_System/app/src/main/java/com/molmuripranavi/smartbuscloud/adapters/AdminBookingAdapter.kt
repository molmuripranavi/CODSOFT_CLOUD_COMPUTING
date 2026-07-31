package com.molmuripranavi.smartbuscloud.adapters

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.molmuripranavi.smartbuscloud.R
import com.molmuripranavi.smartbuscloud.models.Booking
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AdminBookingAdapter(
    private val context: Context,
    private val bookingList: ArrayList<Booking>
) : RecyclerView.Adapter<AdminBookingAdapter.BookingViewHolder>() {

    private val firestore = FirebaseFirestore.getInstance()

    class BookingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtBusName: TextView =
            itemView.findViewById(R.id.txtBusName)

        val txtPassengerName: TextView =
            itemView.findViewById(R.id.txtPassengerName)

        val txtPassengerEmail: TextView =
            itemView.findViewById(R.id.txtPassengerEmail)

        val txtRoute: TextView =
            itemView.findViewById(R.id.txtRoute)

        val txtSeat: TextView =
            itemView.findViewById(R.id.txtSeat)

        val txtFare: TextView =
            itemView.findViewById(R.id.txtFare)

        val txtBookingTime: TextView =
            itemView.findViewById(R.id.txtBookingTime)

        val txtStatus: TextView =
            itemView.findViewById(R.id.txtStatus)

        val btnCancel: MaterialButton =
            itemView.findViewById(R.id.btnCancel)

        val btnDelete: MaterialButton =
            itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookingViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_admin_booking, parent, false)

        return BookingViewHolder(view)
    }

    override fun getItemCount(): Int = bookingList.size

    override fun onBindViewHolder(
        holder: BookingViewHolder,
        position: Int
    ) {

        val booking = bookingList[position]

        holder.txtBusName.text = booking.busName

        holder.txtPassengerName.text =
            "Passenger : ${booking.passengerName}"

        holder.txtPassengerEmail.text =
            "Email : ${booking.passengerEmail}"

        holder.txtRoute.text =
            "${booking.from} → ${booking.to}"

        holder.txtSeat.text =
            "Seat : ${booking.seatNumber}"

        holder.txtFare.text =
            "Fare : ₹${booking.fare}"

        val sdf = SimpleDateFormat(
            "dd MMM yyyy  hh:mm a",
            Locale.getDefault()
        )

        holder.txtBookingTime.text =
            "Booked : ${sdf.format(Date(booking.bookingTime))}"

        holder.txtStatus.text = booking.status

        if (booking.status == "Cancelled") {

            holder.txtStatus.setTextColor(Color.RED)

            holder.btnCancel.isEnabled = false

            holder.btnCancel.text = "Cancelled"

        } else {

            holder.txtStatus.setTextColor(
                Color.parseColor("#2E7D32")
            )
        }

        holder.btnCancel.setOnClickListener {

            AlertDialog.Builder(context)
                .setTitle("Cancel Booking")
                .setMessage("Are you sure you want to cancel this booking?")
                .setPositiveButton("Yes") { _, _ ->

                    firestore.collection("bookings")
                        .document(booking.id)
                        .update("status", "Cancelled")
                        .addOnSuccessListener {

                            firestore.collection("buses")
                                .document(booking.busId)
                                .update(
                                    "availableSeats",
                                    FieldValue.increment(1)
                                )

                            booking.status = "Cancelled"

                            notifyItemChanged(position)

                            Toast.makeText(
                                context,
                                "Booking Cancelled Successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }
                .setNegativeButton("No", null)
                .show()
        }

        holder.btnDelete.setOnClickListener {

            AlertDialog.Builder(context)
                .setTitle("Delete Booking")
                .setMessage("Delete this booking permanently?")
                .setPositiveButton("Delete") { _, _ ->

                    firestore.collection("bookings")
                        .document(booking.id)
                        .delete()
                        .addOnSuccessListener {

                            bookingList.removeAt(position)

                            notifyItemRemoved(position)

                            notifyItemRangeChanged(
                                position,
                                bookingList.size
                            )

                            Toast.makeText(
                                context,
                                "Booking Deleted",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }
}
