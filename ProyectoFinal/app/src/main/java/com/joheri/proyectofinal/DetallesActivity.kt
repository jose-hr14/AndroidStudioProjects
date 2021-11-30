package com.joheri.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.joheri.proyectofinal.databinding.ActivityBibliotecaBinding
import com.joheri.proyectofinal.databinding.ActivityDetallesBinding

class DetallesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetallesBinding
    private lateinit var  juego: Juego
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
            db.updateJuego(juego.codigo!!, juego )

        }

        binding.borrarButton.setOnClickListener()
        {
            val db = MyDBOpenHelper(this, null)
            val juego = Juego(intent.getStringExtra("codigo")?.toInt(), binding.nombreET2.text.toString(), binding.generoET2.text.toString(), binding.anyoET2.text.toString().toInt(), binding.companiaET2.text.toString(), binding.consolaET2.text.toString(), intent.getStringExtra("imagen"))
            db.delJuego(juego.codigo!!)
            finish()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu_detalles, menu)
        return true
    }

    /**
     * Método encargado de gestionar las opciones pulsadas del menú.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.e -> {
                Log.d("MENU", "${getString(R.string.op1)} seleccionada")
                myToast("${getString(R.string.op1)} seleccionada")
                true
            }
            R.id.op02 -> { // Esta opción no haría falta, ya que abre un submenú.
                Log.d("MENU", "${getString(R.string.op2)} seleccionada")
                myToast("${getString(R.string.op2)} seleccionada")
                true
            }

            R.id.op021 -> {
                Log.d("MENU", "${getString(R.string.op21)} seleccionada")
                myToast("${getString(R.string.op21)} seleccionada")
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun myToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}