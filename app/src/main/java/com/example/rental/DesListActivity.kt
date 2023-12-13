package com.example.rental

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DesListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_des_list)

        //instance
        val rvDes:RecyclerView = findViewById(R.id.recyclerViewDestinasi)
        val btnBack:ImageView = findViewById(R.id.btnKembali)
        //set layout manager di recycler view
        rvDes.layoutManager = LinearLayoutManager(this)
        //list data destinasi
        val data = ArrayList<DesModel>()
        data.add(DesModel(R.drawable.d1,"Pantai Parangtritis","Pantai Parangtritis juga merupakan salah satu pantai terbaik untuk menikmati sunset,"))
        data.add(DesModel(R.drawable.d2,"Lereng Merapi","Pantai Parangtritis juga merupakan salah satu pantai terbaik untuk menikmati sunset"))
        data.add(DesModel(R.drawable.d3,"Waduk Sermo","Pantai Parangtritis juga merupakan salah satu pantai terbaik untuk menikmati sunset"))
        data.add(DesModel(R.drawable.d4,"Alun-Alun Selatan","Alun-Alun Selatan merupakan salah satu Sultan's Ground yang, sesuai namanya, terletak di selatan Kraton."))
        data.add(DesModel(R.drawable.d5,"Keraton Yogyakarta","Kraton Yogyakarta adalah istananya. Sejarah panjang Kesultanan Yogyakarta pun bisa kamu lihat langsung di sini.\n" +
                "Kamu bisa mengunjungi Kompleks Utama Kraton Yogyakarta yang dibuka untuk umum pada pukul 08.30 - 13.00 WIB, kecuali Jumat hanya sampai 11.00 WIB."))

        //set adapter
        val adapter = AdapterDes(data)
        //set adapter ke recyclerview
        rvDes.adapter = adapter

        //saat diklik kembali
        btnBack.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}