package com.example.rental

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterDes(private val listDes:List<DesModel>):
                        RecyclerView.Adapter<AdapterDes.ViewHolder>() {

    class ViewHolder(ItemView: View):RecyclerView.ViewHolder(ItemView){
        val image:ImageView = ItemView.findViewById(R.id.imageDes)
        val title:TextView = ItemView.findViewById(R.id.JudulDes)
        val desc:TextView = ItemView.findViewById(R.id.DeskripsiDes)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.card_destinasi,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val  modelDes = listDes[position]

        holder.image.setImageResource(modelDes.image)
        holder.title.text = modelDes.title
        holder.desc.text = modelDes.desc
    }

    override fun getItemCount(): Int {
        return listDes.size
    }
}