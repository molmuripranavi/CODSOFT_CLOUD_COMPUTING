package com.molmuripranavi.smartbuscloud.adapters

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.firebase.firestore.FirebaseFirestore
import com.molmuripranavi.smartbuscloud.R
import com.molmuripranavi.smartbuscloud.models.Passenger

class PassengerAdapter(
    private val context: Context,
    private val passengerList: ArrayList<Passenger>
) : RecyclerView.Adapter<PassengerAdapter.PassengerViewHolder>() {

    private val firestore = FirebaseFirestore.getInstance()

    class PassengerViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val txtPassengerName: TextView =
            itemView.findViewById(R.id.txtPassengerName)

        val txtPassengerEmail: TextView =
            itemView.findViewById(R.id.txtPassengerEmail)

        val txtPassengerPhone: TextView =
            itemView.findViewById(R.id.txtPassengerPhone)

        val txtRole: TextView =
            itemView.findViewById(R.id.txtRole)

        val btnDeletePassenger: MaterialButton =
            itemView.findViewById(R.id.btnDeletePassenger)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PassengerViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_passenger, parent, false)

        return PassengerViewHolder(view)
    }

    override fun getItemCount(): Int = passengerList.size

    override fun onBindViewHolder(
        holder: PassengerViewHolder,
        position: Int
    ) {

        val passenger = passengerList[position]

        holder.txtPassengerName.text = passenger.name
        holder.txtPassengerEmail.text = passenger.email
        holder.txtPassengerPhone.text = passenger.phone
        holder.txtRole.text = passenger.role

        holder.btnDeletePassenger.setOnClickListener {

            AlertDialog.Builder(context)
                .setTitle("Delete Passenger")
                .setMessage("Are you sure you want to delete this passenger?")
                .setPositiveButton("Delete") { _, _ ->

                    firestore.collection("users")
                        .document(passenger.id)
                        .delete()
                        .addOnSuccessListener {

                            passengerList.removeAt(position)

                            notifyItemRemoved(position)
                            notifyItemRangeChanged(position, passengerList.size)

                            Toast.makeText(
                                context,
                                "Passenger Deleted Successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        .addOnFailureListener {

                            Toast.makeText(
                                context,
                                it.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }
}