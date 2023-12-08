package com.example.rental


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class DaftarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar)

        //instance text
        val txtNama : EditText = findViewById(R.id.daftarNama)
        val txtNohp : EditText = findViewById(R.id.daftarNohp)
        val txtEmail : EditText = findViewById(R.id.daftarEmail)
        val txtPassword : EditText = findViewById(R.id.daftarPassword)

        //instance
        val btnMasuk:TextView = findViewById(R.id.btnMasuk)

        //instance button daftar
        val btnDaftar : Button = findViewById(R.id.btnDaftar)


        btnDaftar.setOnClickListener {
            val dbhelper = DatabaseHelper(this)

            val email : String = txtEmail.text.toString().trim()
            val nama : String = txtNama.text.toString().trim()
            val nohp : String = txtNohp.text.toString().trim()
            val password : String = txtPassword.text.toString().trim()

            //cek data ->email sudah terdaftar atau belum
            val data:String = dbhelper.checkData(email)
            //jika belum terdaftar
            if (data == ""){
                //insert
                dbhelper.addAccount( email, nama, nohp, password)

                val intentLogin = Intent(this@DaftarActivity, LoginActivity::class.java)
                startActivity(intentLogin)
            } else {
                //email sudah terdaftar
                Toast.makeText(this@DaftarActivity, "Daftar Gagal" + "Email Anda Sudah Terdaftar", Toast.LENGTH_SHORT).show()
            }
        }



        //saat masuk diklik
        btnMasuk.setOnClickListener {
            //balik ke login
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}