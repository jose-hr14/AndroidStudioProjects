package com.joheri.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.joheri.examen.databinding.ActivitySecondBinding
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    private val adapter: RecycleAdapter = RecycleAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configurarRecyclerView()


    }
    // Con esta función, ponemos a punto el recyclerview para mostrarlo en nuestra actividad
    fun configurarRecyclerView()
    {
        val listaLibros = leerFichero()

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.RecycleAdapter(listaLibros, this)
        binding.recyclerView.adapter = adapter
    }

    // Leemos el fichero del historial de personas y convertimos los datos extraídos en objetos
    // de la clase Persona para, posteriormente, añadirlo a un array y devolverlo
    fun leerFichero(): MutableList<Libro> {
        val listaLibros: MutableList<Libro> = ArrayList<Libro>()

        if (fileList().contains("biblioteca.txt")) {

            try {
                val br = BufferedReader(InputStreamReader(openFileInput("biblioteca.txt")))
                var linea = br.readLine()
                while (!linea.isNullOrEmpty())
                {
                    val datos: List<String> = linea.split(";")
                    val nuevoLibro: Libro = Libro(datos[0], datos[1], datos[2])

                    listaLibros.add(nuevoLibro)
                    linea = br.readLine()
                }
                br.close()
            } catch (e: IOException) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "El fichero noe xiste", Toast.LENGTH_LONG).show()
        }
        return listaLibros
    }
    private fun mostrarSnackBar(mensaje: String) {
        Snackbar.make(binding.secondActivityLayout, mensaje, Snackbar.LENGTH_LONG).show()
    }

}