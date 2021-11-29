package com.joheri.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.joheri.proyectofinal.databinding.ActivityBibliotecaBinding
import com.joheri.proyectofinal.databinding.ActivityDetallesBinding

class DetallesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetallesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val codigo = intent.getStringExtra("codigo")?.toInt()
        binding.nombreTV2.text = intent.getStringExtra("nombre")
        binding.generoTV2.text = intent.getStringExtra("genero")
        binding.anyoTV2.text = intent.getStringExtra("año")
        binding.companiaTV2.text = intent.getStringExtra("compañia")
        binding.consolaTV2.text = intent.getStringExtra("consola")
        Glide.with(this).load(intent.getStringExtra("imagen")).into(binding.caratulaImageView2)

    }
}