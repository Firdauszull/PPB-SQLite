package com.example.latihan6

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.latihan6.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    lateinit var binding : ActivityUpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val nim = intent.getStringExtra("nim")!!
        val nama = intent.getStringExtra("nama")!!
        val email = intent.getStringExtra("email")!!
        val password = intent.getStringExtra("password")!!

        val mahasiswa = mahasiswa(email, nama, nim, password)

        binding.inputNim.setText(mahasiswa.nim)
        binding.inputNama.setText(mahasiswa.nama)
        binding.inputEmail.setText(mahasiswa.email)
        binding.inputPassword.setText(mahasiswa.password)

        binding.btnDelete.setOnClickListener(){
            val db = MahasiswaHelper(this)
            db.hapusData(mahasiswa.email)
            setResult(Activity.RESULT_OK)
            finish()
        }
        binding.btnUpdate.setOnClickListener() {
            val updateMahasiswa = mahasiswa(

                binding.inputNim.text.toString(),
                binding.inputNama.text.toString(),
                binding.inputEmail.text.toString(),
                binding.inputPassword.text.toString()

            )
            val db = MahasiswaHelper(this)
            db.updateData(updateMahasiswa)
            setResult(Activity.RESULT_OK)
            finish()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                onBackPressed()
                true
            }else -> super.onOptionsItemSelected(item)
        }

    }
}