package com.example.latihan6

import android.provider.BaseColumns

object MahasiswaContract {
    class MahasiswaEntry : BaseColumns{
        companion object {
            const val Table_nama = "mahasiswa"
            const val Coloums_email = "email"
            const val Coloums_nama = "nama"
            const val Coloums_nim = "nim"
            const val Coloums_password = "password"


        }

    }
}