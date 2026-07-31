package com.molmuripranavi.smartbuscloud.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.firebase.firestore.FirebaseFirestore
import com.molmuripranavi.smartbuscloud.R
import com.molmuripranavi.smartbuscloud.activities.admin.EditBusActivity
import com.molmuripranavi.smartbuscloud.models.Bus

class AdminBusAdapter(
    private val context: Context,
    private var busList: ArrayList<Bus>
) : RecyclerView.Adapter<AdminBusAdapter.BusViewHolder>() {

    private val firestore = FirebaseFirestore.getInstance()

    class BusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtBusName: TextView = itemView.findViewById(R.id.txtBusName)
        val txtBusType: TextView = itemView.findViewById(R.id.txtBusType)
        val txtRoute: TextView = itemView.findViewById(R.id.txtRoute)
        val txtTiming: TextView = itemView.findViewById(R.id.txtTiming)
        val txtFare: TextView = itemView.findViewById(R.id.txtFare)
        val txtSeats: TextView = itemView.findViewById(R.id.txtSeats)

        val btnEdit: MaterialButton = itemView.findViewById(R.id.btnEdit)
        val btnDelete: MaterialButton = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_admin_bus, parent, false)
        return BusViewHolder(view)
    }

    override fun getItemCount(): Int = busList.size

    override fun onBindViewHolder(holder: BusViewHolder, position: Int) {
        val bus = busList[position]

        holder.txtBusName.text = bus.busName
        holder.txtBusType.text = bus.busType
        holder.txtRoute.text = "${bus.from} → ${bus.to}"
        holder.txtTiming.text = "${bus.departure} → ${bus.arrival}"
        holder.txtFare.text = "Fare : ₹${bus.fare}"
        holder.txtSeats.text = "Available Seats : ${bus.availableSeats}"

        holder.btnEdit.setOnClickListener {
            val intent = Intent(context, EditBusActivity::class.java)
            intent.putExtra("busId", bus.id)
            intent.putExtra("busName", bus.busName)
            intent.putExtra("from", bus.from)
            intent.putExtra("to", bus.to)
            intent.putExtra("departure", bus.departure)
            intent.putExtra("arrival", bus.arrival)
            intent.putExtra("fare", bus.fare)
            intent.putExtra("availableSeats", bus.availableSeats)
            intent.putExtra("busType", bus.busType)
            context.startActivity(intent)
        }

        holder.btnDelete.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Delete Bus")
                .setMessage("Are you sure you want to delete this bus?")
                .setPositiveButton("Delete") { _, _ ->
                    firestore.collection("buses")
                        .document(bus.id)
                        .delete()
                        .addOnSuccessListener {
                            busList.removeAt(position)
                            notifyItemRemoved(position)
                            notifyItemRangeChanged(position, busList.size)
                            Toast.makeText(context, "Bus Deleted", Toast.LENGTH_SHORT).show()
                        }
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }

    fun updateList(newList: ArrayList<Bus>) {
        busList = newList
        notifyDataSetChanged()
    }
}
