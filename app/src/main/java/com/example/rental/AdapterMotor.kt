package com.example.rental

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

class AdapterMotor(private val listMotor: List<MotorModel>) : RecyclerView.Adapter<AdapterMotor.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textNoStnk: TextView = itemView.findViewById(R.id.textNoStnk)
        val imageMotor: ImageView = itemView.findViewById(R.id.imageViewMotor)
        val textTitle: TextView = itemView.findViewById(R.id.textNam)
        val textDesc: TextView = itemView.findViewById(R.id.textDes)

        fun bind(data: MotorModel) {
            textNoStnk.text = data.nostnk
            val imageResId = itemView.context.resources.getIdentifier(data.gambar, "drawable", itemView.context.packageName)
            imageMotor.setImageResource(imageResId)
            textTitle.text = data.merktipe
            textDesc.text = "${data.kondisi} - ${data.tahun} - ${data.harga}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout_motor, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMotor[position])
    }

    override fun getItemCount(): Int {
        return listMotor.size
    }
}
