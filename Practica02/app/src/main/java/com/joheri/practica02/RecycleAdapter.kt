package com.joheri.practica02

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joheri.practica02.databinding.HistorialPersonasBinding

//José Hernández Riquelme
class RecycleAdapter: RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {
    private var listaPersonas: MutableList<Persona> = ArrayList()
    private lateinit var context: Context

    //Metemos la lista de personas y el contexto en el RecyclerAdapter
    fun RecycleAdapter(listaPersonas: MutableList<Persona>, context: Context)
    {
        this.listaPersonas = listaPersonas
        this.context = context
    }

    // En la class view holder, creamos la función bind, que une cada
    // las propiedades de cada persona con su respectivo elemento
    // en el layout historial_personas.xml
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = HistorialPersonasBinding.bind(view)

        fun bind(itemPersona: Persona) {
            binding.nombreTextView.text = itemPersona.nombre
            binding.generoTextView.text = itemPersona.genero
            binding.diaTextView.text = itemPersona.dia
            binding.mesTextView.text = itemPersona.mes
            binding.anyoTextView.text = itemPersona.anyo
            binding.edadNumTextView.text = itemPersona.edad
        }
    }

    // Funcion que infla el el viewHolder con el layout de historial_personas.xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(HistorialPersonasBinding.inflate(layoutInflater, parent, false).root)
    }

    // Cada una de las personas de la lista, las unimos con el layout historial_personas.xml
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemPersona = listaPersonas.get(position)
        holder.bind(itemPersona)
    }

    // Devuelve el número de personas de la lista, o el número de elementos que tiene el recycler
    // view
    override fun getItemCount(): Int {
        return listaPersonas.size
    }
}