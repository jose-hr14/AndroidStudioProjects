package com.joheri.examen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.joheri.examen.databinding.ActivityMainBinding
import java.io.IOException
import java.io.PrintWriter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIntroducirLibro.setOnClickListener(){
            introducirLibro()
        }
        binding.btnBiblioteca.setOnClickListener()
        {
            lanzarBiblioteca()
        }
    }
    fun introducirLibro()
    {
        esconderTeclado()
        if(binding.editTextTitulo.text.isNotEmpty() && binding.editTextAutor.text.isNotEmpty())
        {
            var formato = ""

            if(binding.radioGroup.checkedRadioButtonId == binding.rbtnPapel.id)
                formato = "Papel"
            if(binding.radioGroup.checkedRadioButtonId == binding.rbtnEbook.id)
                formato = "eBook"

            var libro = Libro(binding.editTextTitulo.text.toString(), binding.editTextAutor.text.toString(), formato)

            escribirEnFichero(libro)
            binding.editTextAutor.text.clear()
            binding.editTextTitulo.text.clear()
        }
        else
        {
            mostrarSnackBar("Faltan datos")
        }
    }
    fun lanzarBiblioteca()
    {
        esconderTeclado()
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }
    fun esconderTeclado()
    {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
    }
    fun escribirTextoFormateado(libro: Libro): String{
        val textoFichero: String = libro.autor + ";" + libro.titulo + ";" + libro.formato
        return textoFichero
    }
    fun escribirEnFichero(libro: Libro) {
        try {
            val writer = PrintWriter(openFileOutput("biblioteca.txt", MODE_APPEND))
            var datos = escribirTextoFormateado(libro)
            writer.println(datos)
            writer.flush()
            writer.close()
            mostrarSnackBar("Libro guardado correctamente")
        } catch (e: IOException) {
            mostrarSnackBar(e.message!!)
        }
    }
    private fun mostrarSnackBar(mensaje: String) {
        Snackbar.make(binding.mainActivityLayout, mensaje, Snackbar.LENGTH_LONG).show()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.op01 -> {
                introducirLibro()
                true
            }
            R.id.op02 -> {
                lanzarBiblioteca()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}