package com.example.rental

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailAkunActivity : AppCompatActivity() {
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
        val btnKembali:ImageView =findViewById(R.id.btnBack)

        btnEdit.setOnClickListener{
            EditAkunActivity.email = textEmail.text.toString()
            EditAkunActivity.nama = textNama.text.toString()
            EditAkunActivity.nohp = textNoHp.text.toString()
            EditAkunActivity.password = textPass.text.toString()
            val editIntent = Intent(this,EditAkunActivity::class.java)
            startActivity(editIntent)
        }

        btnKembali.setOnClickListener {
            val intent = Intent(this,AkunFragment::class.java)
            startActivity(intent)
        }
    }

   // @SuppressLint("MissingInflatedId")
    //override fun onCreateView(
      //  inflater: LayoutInflater, container: ViewGroup?,
        //savedInstanceState: Bundle?
    //): View? {
      //  val view = inflater.inflate(R.layout.activity_detail_akun, container, false)

        //val textNama : TextView = view.findViewById(R.id.boxNama)
       // textNama.text = HomeFragment.nama

       // val textEmail : TextView = view.findViewById(R.id.boxEmail)
        // textEmail.text = HomeFragment.email

       // val textNoHp : TextView = view.findViewById(R.id.boxNohp)
       // textNoHp.text = HomeFragment.nohp

       // val textPass : TextView = view.findViewById(R.id.boxPassword)
       // textPass.text = HomeFragment.password
       // return view
   // }

}