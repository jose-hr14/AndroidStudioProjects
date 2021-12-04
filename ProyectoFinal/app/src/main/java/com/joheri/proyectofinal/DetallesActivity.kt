package com.joheri.proyectofinal

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.joheri.proyectofinal.databinding.ActivityDetallesBinding

class DetallesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetallesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getStringExtra("codigo")?.toInt()
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

        binding.actualizarButton.setOnClickListener()
        {
            val db = MyDBOpenHelper(this, null)
            val juego = Juego(intent.getStringExtra("codigo")?.toInt(), binding.nombreET2.text.toString(), binding.generoET2.text.toString(), binding.anyoET2.text.toString().toInt(), binding.companiaET2.text.toString(), binding.consolaET2.text.toString(), intent.getStringExtra("imagen"))
            db.updateJuego(juego.codigo!!, juego )
            modoDetalles()
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
            R.id.editarMenu -> {
                modoEditar()
                true
            }
            R.id.borrarMenu -> { // Esta opción no haría falta, ya que abre un submenú.
                confirmarBorrado("¿Seguro que quieres borrar el juego seleccionado?", "Confirmación")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun modoEditar()
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

        binding.actualizarButton.visibility = View.VISIBLE
    }

    private fun modoDetalles()
    {
        binding.nombreET2.visibility = View.INVISIBLE
        binding.generoET2.visibility = View.INVISIBLE
        binding.anyoET2.visibility = View.INVISIBLE
        binding.companiaET2.visibility = View.INVISIBLE
        binding.consolaET2.visibility = View.INVISIBLE

        binding.nombreTV2.visibility = View.VISIBLE
        binding.generoTV2.visibility = View.VISIBLE
        binding.anyoTV2.visibility = View.VISIBLE
        binding.companiaTV2.visibility = View.VISIBLE
        binding.consolaTV2.visibility = View.VISIBLE

        binding.actualizarButton.visibility = View.INVISIBLE

        binding.nombreTV2.setText(binding.nombreET2.text)
        binding.generoTV2.setText(binding.generoET2.text)
        binding.anyoTV2.setText(binding.anyoET2.text)
        binding.companiaTV2.setText(binding.companiaET2.text)
        binding.consolaTV2.setText(binding.consolaET2.text)
    }

    private fun myToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    private fun confirmarBorrado(message: String, title: String) {
        val builder = AlertDialog.Builder(this)
        builder.apply {
            // Se asigna un título.
            setTitle(title)
            // Se asgina el cuerpo del mensaje.
            setMessage(message)
            // Se define el comportamiento de los botones.
            setPositiveButton(android.R.string.ok) {_, _, ->
                val db = MyDBOpenHelper(context, null)
                val juego = Juego(intent.getStringExtra("codigo")?.toInt(), binding.nombreET2.text.toString(), binding.generoET2.text.toString(), binding.anyoET2.text.toString().toInt(), binding.companiaET2.text.toString(), binding.consolaET2.text.toString(), intent.getStringExtra("imagen"))
                db.delJuego(juego.codigo!!)
                myToast("Elemento borrado")
                finish()
            }
            setNegativeButton(android.R.string.no) { _, _ ->
            }
        }
        // Se muestra el AlertDialog.
        builder.show()
    }
}