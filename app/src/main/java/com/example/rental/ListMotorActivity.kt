package com.example.rental


import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListMotorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_motor)
        //instance
        val rvMotor:RecyclerView = findViewById(R.id.recyclerViewMotor)
        val btnBack:ImageView = findViewById(R.id.btnKembali)
        //set layout manager di RecyclerView
        rvMotor.layoutManager = LinearLayoutManager(this)
        //list data motor

        val dbhelper = DatabaseHelper(this)
        val dataMotor = dbhelper.showMotor()

        //set adapter
        val adapter = AdapterMotor(dataMotor)
        //set adapter ke recyclerView
        rvMotor.adapter = adapter


        //setelah btn back di klik
        btnBack.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }

}