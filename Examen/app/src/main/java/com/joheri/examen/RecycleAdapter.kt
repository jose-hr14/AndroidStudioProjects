package com.joheri.examen

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.joheri.examen.databinding.ActivitySecondBinding
import com.joheri.examen.databinding.TarjetaLibroBinding

//José Hernández Riquelme
class RecycleAdapter: RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {
    private lateinit var listaLibros: MutableList<Libro>
    private lateinit var context: Context
    private lateinit var binding: ActivitySecondBinding

    companion object{
        var titulo: String? = null
        var autor: String? = null
        var formato: String? = null
    }

    //Metemos la lista de personas y el contexto en el RecyclerAdapter
    fun RecycleAdapter(listaPersonas: MutableList<Libro>, context: Context)
    {
        this.listaLibros = listaPersonas
        this.context = context
    }

    // En la class view holder, creamos la función bind, que une cada
    // las propiedades de cada persona con su respectivo elemento
    // en el layout historial_personas.xml
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding =  TarjetaLibroBinding.bind(view)

        fun bind(itemLibro: Libro, context: Context) {
            binding.tituloTextView.text = itemLibro.titulo
            binding.autorTextView.text = itemLibro.autor
            binding.formatoTextView.text = itemLibro.formato

            itemView.setOnClickListener {
                Snackbar.make(binding.root, itemLibro.titulo.toString(), Snackbar.LENGTH_LONG).show()
            }
        }
    }

    // Funcion que infla el el viewHolder con el layout de historial_personas.xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(TarjetaLibroBinding.inflate(layoutInflater, parent, false).root)
    }

    // Cada una de las personas de la lista, las unimos con el layout historial_personas.xml
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemLibro = listaLibros.get(position)
        holder.bind(itemLibro, context)
    }

    // Devuelve el número de personas de la lista, o el número de elementos que tiene el recycler
    // view
    override fun getItemCount(): Int {
        return listaLibros.size
    }
}