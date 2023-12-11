package com.example.rental

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListMotorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_motor)
        //instance
        val rvMotor:RecyclerView = findViewById(R.id.recyclerViewMotor)
        //set layout manager di RecyclerView
        rvMotor.layoutManager = LinearLayoutManager(this)
        //list data motor
        val data = ArrayList<MotorModel>()
        data.add(MotorModel(R.drawable.m1,"Vespa Biru","Lorem Ipsum Dolor Sit Amet"))
        data.add(MotorModel(R.drawable.m2,"Vespa Hijau","Lorem Ipsum Dolor Sit Amet"))
        data.add(MotorModel(R.drawable.m3,"Vespa Tosca","Lorem Ipsum Dolor Sit Amet"))
        data.add(MotorModel(R.drawable.m4,"Vespa Merah","Lorem Ipsum Dolor Sit Amet"))
        data.add(MotorModel(R.drawable.m5,"Vespa Kuning","Lorem Ipsum Dolor Sit Amet"))
        //set adapter
        val adapter = AdapterMotor(data)
        //set adapter ke recyclerView
        rvMotor.adapter = adapter


    }

}