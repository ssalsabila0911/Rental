package com.example.rental

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.Toast
import java.io.ByteArrayOutputStream
import java.io.File

class DatabaseHelper(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION)
{
companion object{
    private val DATABASE_NAME = "rentalmotor"
    private val DATABASE_VERSION = 1

    //tabel akun
    private val TABLE_ACCOUNT = "akun"
    //kolom tabel akun
    private val COLUMN_EMAIL = "email"
    private val COLUMN_NAMA = "namalengkap"
    private val COLUMN_NOHP = "nohp"
    private val COLUMN_PASSWORD = "password"

    // tabel motor
    private val TABLE_MOTOR = "motor"
    private val COLUMN_NOSTNK = "nostnk"
    private val COLUMN_NO_MESIN = "nomesin"
    private val COLUMN_MERK_TIPE = "merktipe"
    private val COLUMN_TAHUN = "tahun"
    private val COLUMN_KONDISI = "kondisi"
    private val COLUMN_GAMBAR = "gambar"

}
//create tabel akun
private val CREATE_AKUN_TABLE = ("CREATE TABLE " + TABLE_ACCOUNT + "(" + COLUMN_EMAIL + " TEXT PRIMARY KEY, "+ COLUMN_NAMA +" TEXT, "+ COLUMN_NOHP + " TEXT, "+ COLUMN_PASSWORD +" TEXT)")
//create tabel motor
private val CREATE_MOTOR_TABLE = ("CREATE TABLE " + TABLE_MOTOR + "(" + COLUMN_NOSTNK + " TEXT PRIMARY, " + COLUMN_NO_MESIN + " TEXT, " + COLUMN_MERK_TIPE + " TEXT, " + COLUMN_TAHUN + " TEXT, " + COLUMN_KONDISI + " TEXT, " + COLUMN_GAMBAR + " BLOB)")
//drop tabel akun
private val DROP_AKUN_TABLE = "DROP TABLE IF EXISTS $TABLE_ACCOUNT"
//drop tabel motor
private val STATIC_MOTOR_DATA = arrayOf(
    arrayOf("AB 2991 CD", "753641", "Honda Beat", "2021", "Baik", "path_gambar_1"),
    arrayOf("AB 2342 JK", "290871", "Honda Scoopy", "2020", "baik", "path_gambar_2"),
)

override fun onCreate(p0: SQLiteDatabase?) {
    p0?.execSQL(CREATE_AKUN_TABLE)
}

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL(DROP_AKUN_TABLE)
        onCreate(p0)
    }


    private fun addMotorStatis(database: SQLiteDatabase?, dataMotor : Array<String>){
        val values = ContentValues()
        values.put(COLUMN_NOSTNK, dataMotor[0])
        values.put(COLUMN_NO_MESIN, dataMotor[1])
        values.put(COLUMN_MERK_TIPE, dataMotor[2])
        values.put(COLUMN_TAHUN, dataMotor[3])
        values.put(COLUMN_KONDISI, dataMotor[4])

        //ubah path gambar menjadi byte array
        val imagePath = dataMotor[5]
        val imageFile = File(imagePath)

        if (imageFile.exists()) {
            val bitmap = BitmapFactory.decodeFile(imageFile.absolutePath)

            val byteOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteOutputStream)
            val imageInByte: ByteArray = byteOutputStream.toByteArray()

            values.put(COLUMN_GAMBAR, imageInByte)
        }
        //insert data motor kedalam tabel
        database?.insert(TABLE_MOTOR, null, values)
    }
//login check
fun checkLogin(email:String, password:String):Boolean{
    val column = arrayOf(COLUMN_NAMA)
    val db = this.readableDatabase

//yang dipilih
    val select = "$COLUMN_EMAIL = ? AND $COLUMN_PASSWORD = ?"

//selection argument
    val selectArg = arrayOf(email, password)

    val cursor = db.query(TABLE_ACCOUNT, column, select, selectArg, null, null, null)

    val cursorCount = cursor.count
    cursor.close()
    db.close()

//cek data
    if(cursorCount > 0){
        return true}
    else {
        return false}
}

fun addAccount(email:String, namalengkap:String, nohp:String, password:String){
    val db = this.readableDatabase

    val values = ContentValues()
    values.put(COLUMN_EMAIL, email)
    values.put(COLUMN_NAMA, namalengkap)
    values.put(COLUMN_NOHP, nohp)
    values.put(COLUMN_PASSWORD, password)

    val result = db.insert(TABLE_ACCOUNT, null, values)

    if(result==(0).toLong()){
        Toast.makeText(context, "Pendaftaran Gagal", Toast.LENGTH_SHORT).show()
    } else{
        Toast.makeText(context, "Pendaftaran Berhasil, " + "Silahkan masuk dengan akun baru Anda!", Toast.LENGTH_SHORT).show()
    }
    db.close()
}


@SuppressLint("Range")
fun checkData(email:String):String{
    val column = arrayOf(COLUMN_NAMA)
    val db = this.readableDatabase
    val select = "$COLUMN_EMAIL = ?"
    val selectArg = arrayOf(email)
    var nama:String =""

    val cursor = db.query(TABLE_ACCOUNT, column, select, selectArg, null, null, null)

    if(cursor.moveToFirst()){
        nama = cursor.getString(cursor.getColumnIndex(COLUMN_NAMA))
    }
    cursor.close()
    db.close()
    return nama
}

@SuppressLint("Range")
fun getMotorData(nostk :String): MotorModel? {
    val db = this.readableDatabase
    val cursor = db.query(TABLE_MOTOR,null,"$COLUMN_NOSTNK = ?", arrayOf(nostk),null,null,null)

    var dataMotor: MotorModel? = null

    if (cursor.moveToFirst()) {
        val imageByteArray = cursor.getBlob(cursor.getColumnIndex(COLUMN_GAMBAR))
        dataMotor = MotorModel(
            cursor.getString(cursor.getColumnIndex(COLUMN_NOSTNK)),
            cursor.getString(cursor.getColumnIndex(COLUMN_NO_MESIN)),
            cursor.getString(cursor.getColumnIndex(COLUMN_MERK_TIPE)),
            cursor.getString(cursor.getColumnIndex(COLUMN_TAHUN)),
            cursor.getString(cursor.getColumnIndex(COLUMN_KONDISI)),
            imageByteArray
        )
    }
    cursor.close()
    db.close()

    return dataMotor
}
}