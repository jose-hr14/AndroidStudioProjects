package com.joheri.practica02

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joheri.practica02.databinding.HistorialPersonasBinding


class RecycleAdapter: RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {
    private var listaPersonas: MutableList<Persona> = ArrayList()
    private lateinit var context: Context


    fun RecycleAdapter(listaPersonas: MutableList<Persona>, context: Context)
    {
        this.listaPersonas = listaPersonas
        this.context = context
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = HistorialPersonasBinding.bind(view)

        fun bind(itemPersona: Persona, context: Context) {
            binding.nombreTextView.text = itemPersona.nombre
            binding.generoTextView.text = itemPersona.genero
            binding.diaTextView.text = itemPersona.dia
            binding.mesTextView.text = itemPersona.mes
            binding.anyoTextView.text = itemPersona.anyo
            binding.edadNumTextView.text = itemPersona.edad
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(HistorialPersonasBinding.inflate(layoutInflater, parent, false).root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemPersona = listaPersonas.get(position)
        holder.bind(itemPersona, context)
    }

    override fun getItemCount(): Int {
        return listaPersonas.size
    }
}