package com.example.rental

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rental.AkunFragment.Companion.email
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class PembayaranActivity : AppCompatActivity() {

    private lateinit var textTanggal: EditText
    private val calendar = Calendar.getInstance()
    companion object{
        var tanggal = ""
        var lama = 1
        var noPlat = "AB1111"
        var harga = 10000
        var total = 10000
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran)

        val btnBack:ImageView = findViewById(R.id.btnBack)
        textTanggal = findViewById(R.id.TglRent)
        val textLama:EditText = findViewById(R.id.LamaRent)
        val textNoPlat:EditText = findViewById(R.id.Noplat)
        val textHarga:EditText = findViewById(R.id.Harga)
        val buttonRental:Button = findViewById(R.id.btnRental)
        val textEmail:EditText = findViewById(R.id.IdUser)
        val textTotal:EditText = findViewById(R.id.TotalRent)

        //isi data
        textNoPlat.setText(noPlat.toString())
        textHarga.setText(harga.toString())
        textEmail.setText(HomeFragment.email)
        total = harga * lama
        textTotal.setText(total.toString())

        buttonRental.setOnClickListener {
            tanggal = textTanggal.text.toString()
            lama = textLama.text.toString().toInt()
            noPlat = textNoPlat.text.toString()
            harga = textHarga.text.toString().toInt()
            email = textEmail.text.toString()



            val dbHelper = DatabaseHelper(this)
            dbHelper.addRental()

            val intent = Intent(this, ListMotorActivity::class.java)
            startActivity(intent)
        }

        btnBack.setOnClickListener {
            val intent = Intent(this,ListMotorActivity::class.java)
            startActivity(intent)
        }

        textTanggal.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun showDatePickerDialog(){
        val datePickerDialog = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { _, year, montOfYear, dayOfMonth ->
                val selecDate = Calendar.getInstance()
                selecDate.set(year, montOfYear, dayOfMonth)

                if (selecDate >= Calendar.getInstance()){
                    updateDateInView(selecDate)
                } else {
                    Toast.makeText(this, "Pilih Kembali Tanggal", Toast.LENGTH_SHORT).show()
                }
            },
            calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun updateDateInView(calendar: Calendar){
        val myFormat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        textTanggal.setText(sdf.format(calendar.time))
    }
}