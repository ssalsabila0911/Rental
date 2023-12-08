package com.example.rental

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterMotor(private val listMotor:List<MotorModel>):
                        RecyclerView.Adapter<AdapterMotor.ViewHolder>() {

    class ViewHolder(ItemView: View):RecyclerView.ViewHolder(ItemView){
      val image:ImageView = ItemView.findViewById(R.id.imageViewMotor)
      val title:TextView = ItemView.findViewById(R.id.textNam)
        val desc:TextView = ItemView.findViewById(R.id.textDes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.card_layout_motor,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
       val modelMotor = listMotor[position]

        holder.image.setImageResource(modelMotor.image)
        holder.title.text = modelMotor.title
        holder.desc.text = modelMotor.desc
    }

    override fun getItemCount(): Int {
       return listMotor.size
    }
}