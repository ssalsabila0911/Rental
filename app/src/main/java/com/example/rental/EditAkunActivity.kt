package com.example.rental

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditAkunActivity : AppCompatActivity() {

    companion object{
        var nama = ""
        var email = ""
        var nohp = ""
        var password = ""
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_akun)

        getSupportActionBar()?.hide()

        //instance
        val textEmail : EditText = findViewById(R.id.EditEmail)
        val textNama : EditText = findViewById(R.id.EditNama)
        val textNoHp : EditText = findViewById(R.id.EditNohp)
        val textPass : EditText = findViewById(R.id.EditPassword)
        val btnSimpan : Button = findViewById(R.id.btnSimpan)
        val btnKembali : ImageView = findViewById(R.id.btnBack)

        //input
        textEmail.setText(email)
        textNama.setText(nama)
        textNoHp.setText(nohp)
        textPass.setText(password)

        //event btn simpan
        btnSimpan.setOnClickListener{
            val dbhelper = DatabaseHelper(this)

            val email : String = textEmail.text.toString()
            val nama : String = textNama.text.toString()
            val nohp : String = textNoHp.text.toString()
            val pass : String = textPass.text.toString()

            val updateAkun = AkunModel(email, nama, nohp, pass)
            dbhelper.updateAkun(updateAkun)

            val intent = Intent(this, DetailAkunActivity::class.java)
            startActivity(intent)
        }

        btnKembali.setOnClickListener {
            val intent = Intent(this,DetailAkunActivity::class.java)
            startActivity(intent)
        }
    }


}