package com.example.rental

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DatabaseHelper(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION)
{
companion object{
    private val DATABASE_NAME = "rentalmotor"
    private val DATABASE_VERSION = 3

    //tabel akunya
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
    private val COLUMN_HARGA = "harga"
    private val COLUMN_GAMBAR_MOTOR = "gambar"

    // tabel rental
    //private val TABLE_RENTAL = "rental"
    //private val COLUMN_ID_RENTAL = "idrental"
    //private val COLUMN_TGL_RENTAL = "tglrental"
    //private val COLUMN_LAMA_RENTAL = "lamarental"
    //private val COLUMN_HARGA_RENTAL = "harga"
    //private val COLUMN_PLAT_MOTOR = "platmotor"
    //private val COLUMN_EMAIL_USER = "emailuser"


}//CREATE
//tabel akun
private val CREATE_AKUN_TABLE = ("CREATE TABLE " + TABLE_ACCOUNT + "(" + COLUMN_EMAIL + " TEXT PRIMARY KEY, "+ COLUMN_NAMA +" TEXT, "+ COLUMN_NOHP + " TEXT, "+ COLUMN_PASSWORD +" TEXT)")
//tabel motor
private val CREATE_MOTOR_TABLE = ("CREATE TABLE " + TABLE_MOTOR + "(" + COLUMN_NOSTNK + " TEXT PRIMARY KEY, " + COLUMN_NO_MESIN + " TEXT, " + COLUMN_MERK_TIPE + " TEXT, " + COLUMN_TAHUN + " TEXT, " + COLUMN_KONDISI + " TEXT, " + COLUMN_HARGA + " TEXT, "+ COLUMN_GAMBAR_MOTOR + " TEXT)")
//tabel rental
//private val CREATE_RENTAL_TABLE = ("CREATE TABLE " + TABLE_RENTAL + "(" + COLUMN_ID_RENTAL + " TEXT PRIMARY KEY, " + COLUMN_TGL_RENTAL + " TEXT, " + COLUMN_LAMA_RENTAL + " TEXT, " + COLUMN_HARGA_RENTAL + " TEXT, " + COLUMN_PLAT_MOTOR + " TEXT, " + COLUMN_EMAIL_USER + " TEXT)")
//DROP
//tabel akun
private val DROP_AKUN_TABLE = "DROP TABLE IF EXISTS $TABLE_ACCOUNT"
//tabel motor
private  val DROP_MOTOR_TABLE = "DROP TABLE IF EXISTS $TABLE_MOTOR"
// tabel rental
//private val DROP_RENTAL_TABLE = "DROP TABLE IF EXISTS $TABLE_RENTAL"



//INSERT
private val INSERT_MOTOR1 = ("INSERT INTO $TABLE_MOTOR VALUES ('AB 2991 CD', '753641', 'Honda Beat', '2021', 'Baik', '80000','m1')")
    private val INSERT_MOTOR2 = ("INSERT INTO $TABLE_MOTOR VALUES ('AB 2981 DE', '756342', 'Honda Vario', '2022', 'Baik', '90000','m2')")
    private val INSERT_MOTOR3 = ("INSERT INTO $TABLE_MOTOR VALUES ('AB 2971 EF', '756343', 'Honda Scoopy', '2020', 'Baik', '85000','m3')")
    private val INSERT_MOTOR4 = ("INSERT INTO $TABLE_MOTOR VALUES ('AB 2961 GH', '756344', 'Yamaha Mio', '2019', 'Baik', '75000','m4')")
    private val INSERT_MOTOR5 = ("INSERT INTO $TABLE_MOTOR VALUES ('AB 2951 IJ', '756345', 'Yamaha Filano', '2023', 'Baik', '90000','m5')")

override fun onCreate(p0: SQLiteDatabase?) {
    p0?.execSQL(CREATE_AKUN_TABLE)
    p0?.execSQL(CREATE_MOTOR_TABLE)
    p0?.execSQL(INSERT_MOTOR1)
    p0?.execSQL(INSERT_MOTOR2)
    p0?.execSQL(INSERT_MOTOR3)
    p0?.execSQL(INSERT_MOTOR4)
    p0?.execSQL(INSERT_MOTOR5)
    //p0?.execSQL(CREATE_RENTAL_TABLE)
}

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL(DROP_AKUN_TABLE)
        p0?.execSQL(DROP_MOTOR_TABLE)
        //p0?.execSQL(DROP_RENTAL_TABLE)
        onCreate(p0)
    }


//login check
@SuppressLint("Range")
fun checkLogin(email:String, password:String):Boolean{
    val column = arrayOf(COLUMN_NAMA, COLUMN_EMAIL, COLUMN_NOHP, COLUMN_PASSWORD)
    val db = this.readableDatabase

//yang dipilih
    val select = "$COLUMN_EMAIL = ? AND $COLUMN_PASSWORD = ?"

//selection argument
    val selectArg = arrayOf(email, password)

    val cursor = db.query(TABLE_ACCOUNT, column, select, selectArg, null, null, null)

    val cursorCount = cursor.count
    val result : Boolean

    if (cursorCount > 0) {
        result = true

        if(cursor.moveToFirst()){
            HomeFragment.nama = cursor.getString(cursor.getColumnIndex(COLUMN_NAMA))
            HomeFragment.email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL))
            HomeFragment.nohp = cursor.getString(cursor.getColumnIndex(COLUMN_NOHP))
            HomeFragment.password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD))
        }
    }
    else {
        result = false
    }
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

fun updateAkun(menu: AkunModel){
    val db = this.writableDatabase
    val values = ContentValues()

    values.put(COLUMN_EMAIL, menu.email)
    values.put(COLUMN_NAMA, menu.nama)
    values.put(COLUMN_NOHP, menu.nohp)
    values.put(COLUMN_PASSWORD, menu.pass)

    val result = db.update(TABLE_ACCOUNT, values, COLUMN_EMAIL + " = ? ", arrayOf(menu.email.toString())).toLong()
    //
    if (result==(0).toLong()){
        Toast.makeText(context, "Edit Gagal", Toast.LENGTH_SHORT).show()
    } else {
        Toast.makeText(context, "Edit Berhasil", Toast.LENGTH_SHORT).show()
    }
    db.close()
}

@SuppressLint("Range")
fun showMotor():ArrayList<MotorModel>{
    val listMotor = ArrayList<MotorModel>()
    val db = this.readableDatabase
    var cursor:Cursor?=null
    try{
        cursor = db.rawQuery("SELECT * FROM " + TABLE_MOTOR, null)
    }catch (se:SQLException){
        db.execSQL(CREATE_MOTOR_TABLE)
        return ArrayList()
    }

    var nostnk: String
    var nomesin: String
    var merktipe: String
    var tahun: String
    var kondisi: String
    var harga: String
    var gambar: String

    if(cursor.moveToFirst()){
        do {
            nostnk = cursor.getString(cursor.getColumnIndex(COLUMN_NOSTNK))
            nomesin = cursor.getString(cursor.getColumnIndex(COLUMN_NO_MESIN))
            merktipe = cursor.getString(cursor.getColumnIndex(COLUMN_MERK_TIPE))
            tahun = cursor.getString(cursor.getColumnIndex(COLUMN_TAHUN))
            kondisi = cursor.getString(cursor.getColumnIndex(COLUMN_KONDISI))
            harga = cursor.getString(cursor.getColumnIndex(COLUMN_HARGA))

            gambar = cursor.getString(cursor.getColumnIndex(COLUMN_GAMBAR_MOTOR))

            val model = MotorModel (nostnk = nostnk, nomesin = nomesin, merktipe = merktipe, tahun = tahun, kondisi = kondisi, harga = harga, gambar = gambar )
            listMotor.add(model)
        }while (cursor.moveToNext())
    }
    return listMotor

}


}