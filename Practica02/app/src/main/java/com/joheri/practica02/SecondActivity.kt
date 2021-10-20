package com.joheri.practica02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.joheri.practica02.databinding.ActivitySecondBinding
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
    fun configurarRecyclerView()
    {
        var listaPersonas = leerFichero()

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.RecycleAdapter(listaPersonas, this)
        binding.recyclerView.adapter = adapter
    }

    fun leerFichero(): MutableList<Persona> {
        var ext = ""
        var listaPersonas: MutableList<Persona> = ArrayList<Persona>()
        // Se comprueba si existe el fichero.
        if (fileList().contains(getString(R.string.filename))) {

            try {
                val entrada = InputStreamReader(openFileInput(getString(R.string.filename)))
                val br = BufferedReader(entrada)
                // Leemos la primera línea
                var linea = br.readLine()
                while (!linea.isNullOrEmpty())
                {
                    // Obtenemos los datos separandolo por el ;
                    val datos: List<String> = linea.split(";")
                    var nuevaPersona: Persona = Persona()
                    // Montamos el texto a mostrar
                    // y lo añadimos al textView
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
                entrada.close()
            } catch (e: IOException) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, getString(R.string.ficheroNoExiste), Toast.LENGTH_LONG).show()
        }
        return listaPersonas
    }

}