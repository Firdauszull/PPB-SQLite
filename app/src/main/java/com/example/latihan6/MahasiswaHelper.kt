package com.example.latihan6

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract.CommonDataKinds.Email
import java.lang.invoke.MethodHandle

class MahasiswaHelper(context : Context) : SQLiteOpenHelper(context,DATABASE_NAME,null, DATABASE_VERSION) {
    companion object {
        private val DATABASE_NAME = "mahasiswa.db"
        private val DATABASE_VERSION = 1

        private val SQL_CREATE_ENTRIES = " CREATE TABLE " +
                "${MahasiswaContract.MahasiswaEntry.Table_nama}(" +
                "${MahasiswaContract.MahasiswaEntry.Coloums_email} TEXT PRIMARY KEY, "+
                "${MahasiswaContract.MahasiswaEntry.Coloums_nama} TEXT," +
                "${MahasiswaContract.MahasiswaEntry.Coloums_nim} TEXT, "+
                "${MahasiswaContract.MahasiswaEntry.Coloums_password} TEXT)"

        private val SQL_DELETE_ENTRIES = " DROP TABLE IF EXISTS ${MahasiswaContract.MahasiswaEntry.Table_nama}"

    }

    override fun onCreate(db: SQLiteDatabase?) {
       db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
    fun getData(): ArrayList<mahasiswa>{
        val db = writableDatabase
        val mahasiswaList = ArrayList<mahasiswa>()

        val sql =" SELECT "+
                " ${MahasiswaContract.MahasiswaEntry.Coloums_email}, "+
                " ${MahasiswaContract.MahasiswaEntry.Coloums_nama}, " +
                " ${MahasiswaContract.MahasiswaEntry.Coloums_nim}, " +
                " ${MahasiswaContract.MahasiswaEntry.Coloums_password} " +
                " FROM ${MahasiswaContract.MahasiswaEntry.Table_nama} " +
                " ORDER BY ${MahasiswaContract.MahasiswaEntry.Coloums_nama} ASC"

        val cursor = db.rawQuery(sql, null)

        with(cursor){
            while (moveToNext()){
                val email = getString(getColumnIndexOrThrow(MahasiswaContract.MahasiswaEntry.Coloums_email))
                val nama = getString(getColumnIndexOrThrow(MahasiswaContract.MahasiswaEntry.Coloums_nama))
                val nim = getString(getColumnIndexOrThrow(MahasiswaContract.MahasiswaEntry.Coloums_nim))
                val password = getString(getColumnIndexOrThrow(MahasiswaContract.MahasiswaEntry.Coloums_password))

                val mahasiswa= mahasiswa(email, nama, nim, password)
                mahasiswaList.add(mahasiswa)
            }

        }
        cursor.close()
        return mahasiswaList
    }
    fun insertData (mahasiswa: mahasiswa){
        val db = writableDatabase

        val sql =" INSERT INTO ${MahasiswaContract.MahasiswaEntry.Table_nama}" +
                "( ${MahasiswaContract.MahasiswaEntry.Coloums_email},"+
                " ${MahasiswaContract.MahasiswaEntry.Coloums_nama},"+
                " ${MahasiswaContract.MahasiswaEntry.Coloums_nim},"+
                " ${MahasiswaContract.MahasiswaEntry.Coloums_password})"+
                " VALUES ('${mahasiswa.email}','${mahasiswa.nama}','${mahasiswa.nim}','${mahasiswa.password}')"

        db.execSQL(sql)
        db.close()

        }
    fun hapusData(email: String){
        val db = writableDatabase
        val table = MahasiswaContract.MahasiswaEntry.Table_nama
        val emailColumn = MahasiswaContract.MahasiswaEntry.Coloums_email
        val sql = " DELETE FROM " + table + " WHERE " + emailColumn + " = '" + email + "'"
        db.execSQL(sql)
        db.close()
    }
    fun updateData(mahasiswa: mahasiswa) {
        val db = writableDatabase
        val sql = " UPDATE ${MahasiswaContract.MahasiswaEntry.Table_nama} SET " +
                "${MahasiswaContract.MahasiswaEntry.Coloums_nama} = ' ${mahasiswa.nama}', " +
                "${MahasiswaContract.MahasiswaEntry.Coloums_nim} = ' ${mahasiswa.nim}', " +
                "${MahasiswaContract.MahasiswaEntry.Coloums_email} = ' ${mahasiswa.email}', " +
                "${MahasiswaContract.MahasiswaEntry.Coloums_password} = ' ${mahasiswa.password}' " +
                " WHERE ${MahasiswaContract.MahasiswaEntry.Coloums_email} = '${mahasiswa.email}' "

        db.execSQL(sql)
        db.close()
    }
    }
