package com.joheri.practica02

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.joheri.practica02.databinding.ActivitySecondBinding
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

//José Hernández Riquelme
class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    private val adapter: RecycleAdapter = RecycleAdapter()

    //Inicializamos el binding y llamamos a la función que pone a punto el recyclerview
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configurarRecyclerView()

    }

    // Con esta función, ponemos a punto el recyclerview para mostrarlo en nuestra actividad
    fun configurarRecyclerView()
    {
        val listaPersonas = leerFichero()

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.RecycleAdapter(listaPersonas, this)
        binding.recyclerView.adapter = adapter
    }

    // Leemos el fichero del historial de personas y convertimos los datos extraídos en objetos
    // de la clase Persona para, posteriormente, añadirlo a un array y devolverlo
    fun leerFichero(): MutableList<Persona> {
        val listaPersonas: MutableList<Persona> = ArrayList<Persona>()

        if (fileList().contains(getString(R.string.filename))) {

            try {
                val br = BufferedReader(InputStreamReader(openFileInput(getString(R.string.filename))))
                var linea = br.readLine()
                while (!linea.isNullOrEmpty())
                {
                    val datos: List<String> = linea.split(";")
                    val nuevaPersona: Persona = Persona()

                    nuevaPersona.dia = datos[0]
                    nuevaPersona.mes = datos[1]
                    nuevaPersona.anyo = datos[2]
                    nuevaPersona.nombre = datos[3]
                    nuevaPersona.edad = datos[4]
                    nuevaPersona.genero = datos[5]

                    listaPersonas.add(nuevaPersona)
                    linea = br.readLine()
                }
                br.close()
            } catch (e: IOException) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, getString(R.string.ficheroNoExiste), Toast.LENGTH_LONG).show()
        }
        return listaPersonas
    }

}