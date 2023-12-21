package com.example.rental

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class AdapterOrder(private val listOrder: List<OrderModel>) :
    RecyclerView.Adapter<AdapterOrder.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //
        val txtIdRental : TextView = itemView.findViewById(R.id.tvIdRental)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_order, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //
    }

    override fun getItemCount(): Int {
        return listOrder.size
    }
}