package com.example.rental

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.rental.DaftarActivity
import com.example.rental.HomeFragment
import com.example.rental.R


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        getSupportActionBar()?.hide()

        //instance text
        val txtEmail: EditText = findViewById(R.id.editTextEmail)
        val txtPassword : EditText = findViewById(R.id.editTextPassword)

        //instance
        val btnLogin:Button = findViewById(R.id.btnLogin)
        val btnDaftar:TextView = findViewById(R.id.btnDaftar)


        //saat button login di-klik
        btnLogin.setOnClickListener {
            val dbHelper = DatabaseHelper(this)

            //check data
            val data:String = dbHelper.checkData("syifa@gmail.com")
            Toast.makeText(this@LoginActivity, "Hasil : " + data, Toast.LENGTH_SHORT).show()
            if (data== ""){
                //masukkan data
                dbHelper.addAccount("syifa@gmail.com", "Syifa Salsabila", "08711910033", "inisyifa")
            }
            val email = txtEmail.text.toString().trim()
            val password = txtPassword.text.toString().trim()

            //cek login
            val result:Boolean = dbHelper.checkLogin(email,password)
            if (result == true){
                Toast.makeText(this@LoginActivity, "Login Berhasil", Toast.LENGTH_SHORT).show()
                val intentLogin = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intentLogin)
            } else{
                Toast.makeText(this, "Login Gagal. Silakan Login Kembali !!", Toast.LENGTH_SHORT).show()
                txtEmail.hint = "email"
                txtPassword.hint = "password"
            }

        }
        //saat daftar diklik
        btnDaftar.setOnClickListener {
            //call daftar activity
            val intent = Intent(this,DaftarActivity::class.java)
            startActivity(intent)
        }
    }


}