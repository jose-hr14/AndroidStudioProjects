package com.joheri.proyectofinal

import android.content.Context
import android.database.Cursor
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.joheri.proyectofinal.databinding.ItemRecyclerviewBinding
import android.graphics.drawable.Drawable
import android.widget.ImageView
import java.io.InputStream
import java.lang.Exception
import java.net.URL


class MyRecyclerViewAdapter : RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {

    private lateinit var context: Context
    private lateinit var cursor: Cursor

    fun MyRecyclerViewAdapter(context: Context, cursor: Cursor) {
        this.context = context
        this.cursor = cursor
    }


    /**
     * Se "infla" la vista de los items.
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        Log.d("RECYCLERVIEW", "onCreateViewHolder")
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater.inflate(
                R.layout.item_recyclerview,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return if (cursor != null)
            cursor.count
        else 0
    }


    /**
     * Se completan los datos de cada vista mediante ViewHolder.
     */
    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        // Importante para recorrer el cursor.
        cursor.moveToPosition(position)
        Log.d("RECYCLERVIEW", "onBindViewHolder")
        // Se asignan los valores a los elementos de la UI.
        holder.codigo.text = cursor.getInt(0).toString()
        holder.nombre.text = cursor.getString(1)
        holder.genero.text = cursor.getString(2)
        holder.anyo.text = cursor.getString(3)
        holder.compania.text = cursor.getString(4)
        holder.consola.text = cursor.getString(5)

    }

    inner class ViewHolder : RecyclerView.ViewHolder {
        // Creamos las referencias de la UI.
        val codigo: TextView
        val nombre: TextView
        val genero: TextView
        val anyo: TextView
        val compania: TextView
        val consola: TextView
        val imagen: ImageView

        constructor(view: View) : super(view) {

            // Se enlazan los elementos de la UI mediante ViewBinding.
            val bindingItemsRV = ItemRecyclerviewBinding.bind(view)
            this.codigo = bindingItemsRV.tvIdentificador
            this.nombre = bindingItemsRV.nombreTV
            this.genero = bindingItemsRV.generoTV
            this.anyo = bindingItemsRV.anyoTV
            this.compania = bindingItemsRV.companitaTV
            this.consola = bindingItemsRV.consolaTV
            this.imagen = bindingItemsRV.juegoIMG

            view.setOnClickListener {
                Toast.makeText(context, "${this.codigo.text}-${this.nombre.text} ${this.consola.text}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}