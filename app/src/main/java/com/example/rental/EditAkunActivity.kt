package com.example.rental

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
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
        val textEmail : TextView = findViewById(R.id.EditEmail)
        val textNama : TextView = findViewById(R.id.EditNama)
        val textNoHp : TextView = findViewById(R.id.EditNohp)
        val textPass : TextView = findViewById(R.id.EditPassword)
        val btnSimpan : Button = findViewById(R.id.btnSimpan)

        //input
        textEmail.text = email.toString()
        textNama.text = nama.toString()
        textNoHp.text = nohp.toString()
        textPass.text = password.toString()

        //event btn simpan
        btnSimpan.setOnClickListener{
            val dbhelper = DatabaseHelper(this)

            val email : String = textEmail.toString()
            val nama : String = textNama.toString()
            val nohp : String = textNoHp.toString()
            val pass : String = textPass.toString()

            val updateAkun = AkunModel(email, nama, nohp, pass)
            dbhelper.updateAkun(updateAkun)


            val intent = Intent(this, DetailAkunActivity::class.java)
            startActivity(intent)
        }
    }


}