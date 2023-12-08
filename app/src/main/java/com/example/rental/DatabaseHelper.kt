package com.example.rental

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

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

}
//create tabel akun
private val CREATE_AKUN_TABLE = ("CREATE TABLE " + TABLE_ACCOUNT + "(" + COLUMN_EMAIL + " TEXT PRIMARY KEY, "+ COLUMN_NAMA +" TEXT, "+ COLUMN_NOHP + " TEXT, "+ COLUMN_PASSWORD +" TEXT)")

//drop tabel akun
private val DROP_AKUN_TABLE = "DROP TABLE IF EXISTS $TABLE_ACCOUNT"

override fun onCreate(p0: SQLiteDatabase?) {
    p0?.execSQL(CREATE_AKUN_TABLE)
}

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL(DROP_AKUN_TABLE)
        onCreate(p0)
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
}