package com.example.rental

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailAkunActivity : AppCompatActivity() {
    companion object{
        var nama = ""
        var email = ""
        var nohp = ""
        var password = ""
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_akun)
         val textNama : TextView = findViewById(R.id.boxNama)
        textNama.text = HomeFragment.nama

        val textEmail : TextView = findViewById(R.id.boxEmail)
        textEmail.text = HomeFragment.email

        val textNoHp : TextView = findViewById(R.id.boxNohp)
        textNoHp.text = HomeFragment.nohp

        val textPass : TextView = findViewById(R.id.boxPassword)
        textPass.text = HomeFragment.password


        val btnEdit : Button = findViewById(R.id.btnEdit)
        val btnKem: ImageView = findViewById(R.id.btnBack)

        btnEdit.setOnClickListener{
            EditAkunActivity.email = textEmail.text.toString()
            EditAkunActivity.nama = textNama.text.toString()
            EditAkunActivity.nohp = textNoHp.text.toString()
            EditAkunActivity.password = textPass.text.toString()
            val editIntent = Intent(this,EditAkunActivity::class.java)
            startActivity(editIntent)

        }

        btnKem.setOnClickListener {
            val intent = Intent(this,HomeFragment::class.java)
            startActivity(intent)
        }
    }


}