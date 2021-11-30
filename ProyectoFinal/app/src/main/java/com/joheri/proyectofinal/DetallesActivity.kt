package com.joheri.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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


        binding.nombreET2.setText(intent.getStringExtra("nombre"))
        binding.generoET2.setText(intent.getStringExtra("genero"))
        binding.anyoET2.setText(intent.getStringExtra("año"))
        binding.companiaET2.setText(intent.getStringExtra("compañia"))
        binding.consolaET2.setText(intent.getStringExtra("consola"))

        binding.editarButton.setOnClickListener()
        {
            binding.nombreET2.visibility = View.VISIBLE
            binding.generoET2.visibility = View.VISIBLE
            binding.anyoET2.visibility = View.VISIBLE
            binding.companiaET2.visibility = View.VISIBLE
            binding.consolaET2.visibility = View.VISIBLE

            binding.nombreTV2.visibility = View.INVISIBLE
            binding.generoTV2.visibility = View.INVISIBLE
            binding.anyoTV2.visibility = View.INVISIBLE
            binding.companiaTV2.visibility = View.INVISIBLE
            binding.consolaTV2.visibility = View.INVISIBLE

            binding.borrarButton.visibility = View.VISIBLE
            binding.actualizarButton.visibility = View.VISIBLE
            binding.editarButton.visibility = View.INVISIBLE
        }

        binding.actualizarButton.setOnClickListener()
        {
            val db = MyDBOpenHelper(this, null)
            val juego = Juego(intent.getStringExtra("codigo")?.toInt(), binding.nombreET2.text.toString(), binding.generoET2.text.toString(), binding.anyoET2.text.toString().toInt(), binding.companiaET2.text.toString(), binding.consolaET2.text.toString(), intent.getStringExtra("imagen"))
            db.updateJuego(intent.getStringExtra("codigo")!!.toInt(), juego )

        }

        binding.borrarButton.setOnClickListener()
        {
            val db = MyDBOpenHelper(this, null)
            val juego = Juego(intent.getStringExtra("codigo")?.toInt(), binding.nombreET2.text.toString(), binding.generoET2.text.toString(), binding.anyoET2.text.toString().toInt(), binding.companiaET2.text.toString(), binding.consolaET2.text.toString(), intent.getStringExtra("imagen"))
            db.delJuego(juego.codigo!!)
            finish()
        }


    }
}