package com.example.rental

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterMotor(private val listMotor:List<MotorModel>): RecyclerView.Adapter<AdapterMotor.ViewHolder>() {

    class ViewHolder(ItemView: View):RecyclerView.ViewHolder(ItemView){

        init {
            val textStnk:TextView = ItemView.findViewById(R.id.textNoStnk)
            val image:ImageView = ItemView.findViewById(R.id.imageViewMotor)
            val title:TextView = ItemView.findViewById(R.id.textNam)
            val desc:TextView = ItemView.findViewById(R.id.textDes)
        }

        fun bind(data: MotorModel){
            val nostnk:String = data.nostnk
            val image:String = data.gambar
            val title:String = data.merktipe
            val desc:String = data.kondisi + " - " + data.tahun + " - " + data.harga

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.card_layout_motor,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
       holder.bind(listMotor[position])

        //holder.textStnk.text = modelMotor.nostnk
        //holder.image.setImageURI(Uri.parse("@drawable/" + modelMotor.gambar))
        //holder.title.text = modelMotor.merktipe
        //holder.desc.text = modelMotor.kondisi + " - " + modelMotor.tahun + " - " + modelMotor.harga
    }


    override fun getItemCount(): Int {
       return listMotor.size
    }
}