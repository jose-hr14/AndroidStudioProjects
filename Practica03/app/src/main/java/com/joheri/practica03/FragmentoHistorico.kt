package com.joheri.practica03

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.joheri.practica03.databinding.FragmentoCalculoBinding
import com.joheri.practica03.databinding.FragmentoHistoricoBinding
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

private lateinit var binding: FragmentoHistoricoBinding
private val adapter: RecycleAdapter = RecycleAdapter()

class FragmentoHistorico : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Enlazamos la vista del layout con el código del fragmento
        binding = FragmentoHistoricoBinding.inflate(inflater, container, false)
        //Configuramos y mostramos el recyclerView en el fragmento
        configurarRecyclerView()
        //Devolvemos la vista a la main activity
        return binding.root

    }

    //Volvemos a llamar a la función cuando se retome el fragmento, para que las persona añadidas
    //en tiempo de ejecución aparezcan en recycler view. Recargamos el recycler view con las
    //últimar personas añadidas
    override fun onResume() {
        super.onResume()
        configurarRecyclerView()
    }

    // Con esta función, ponemos a punto el recyclerview para mostrarlo en nuestra actividad
    fun configurarRecyclerView() {
        val listaPersonas = leerFichero()

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter.RecycleAdapter(listaPersonas)
        binding.recyclerView.adapter = adapter
    }

    // Leemos el fichero del historial de personas y convertimos los datos extraídos en objetos
    // de la clase Persona para, posteriormente, añadirlo a un array y devolverlo
    fun leerFichero(): MutableList<Persona> {
        val listaPersonas: MutableList<Persona> = ArrayList<Persona>()

        if (context?.fileList()?.contains(getString(R.string.filename)) == true) {

            try {
                val br =
                    BufferedReader(InputStreamReader(activity?.openFileInput(getString(R.string.filename))))
                var linea = br.readLine()
                while (!linea.isNullOrEmpty()) {
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
                Toast.makeText(activity, e.message, Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(activity, getString(R.string.ficheroNoExiste), Toast.LENGTH_LONG).show()
        }
        return listaPersonas
    }

}