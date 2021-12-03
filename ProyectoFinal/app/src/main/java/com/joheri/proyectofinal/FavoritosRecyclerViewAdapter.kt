package com.joheri.proyectofinal

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joheri.proyectofinal.databinding.ItemRecyclerviewBinding


class FavoritosRecyclerViewAdapter :
    RecyclerView.Adapter<FavoritosRecyclerViewAdapter.ViewHolder>() {

    private lateinit var context: Context
    private lateinit var cursor: Cursor
    private var actionMode: ActionMode? = null

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

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemCount(): Int {
        return cursor.count
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
        Glide.with(context).load(cursor.getString(6)).into(holder.imagen)
        if (cursor.getInt(7) == 1) {
            holder.noFavImageView.visibility = View.GONE
            holder.favImageView.visibility = View.VISIBLE
        }
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
        val posicion: Int
        val noFavImageView: ImageView
        val favImageView: ImageView

        constructor(view: View) : super(view) {
            // Se enlazan los elementos de la UI mediante ViewBinding.
            this.posicion = adapterPosition
            val bindingItemsRV = ItemRecyclerviewBinding.bind(view)
            this.codigo = bindingItemsRV.tvIdentificador
            this.nombre = bindingItemsRV.nombreTV
            this.genero = bindingItemsRV.generoTV
            this.anyo = bindingItemsRV.anyoTV
            this.compania = bindingItemsRV.companitaTV
            this.consola = bindingItemsRV.consolaTV
            this.imagen = bindingItemsRV.juegoIMG
            this.noFavImageView = bindingItemsRV.noFavImageView
            this.favImageView = bindingItemsRV.favImageView

            view.setOnClickListener {
                Toast.makeText(
                    context,
                    "${this.codigo.text}-${this.nombre.text} ${this.consola.text}",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(context, DetallesActivity::class.java).apply {
                    putExtra("codigo", codigo.text)
                    putExtra("nombre", nombre.text)
                    putExtra("genero", genero.text)
                    putExtra("año", anyo.text)
                    putExtra("compañia", compania.text)
                    putExtra("consola", consola.text)
                    cursor.moveToPosition(position)
                    putExtra("imagen", cursor.getString(6))
                }
                startActivity(context, intent, null)
            }
            itemView.setOnLongClickListener {
                when (actionMode) {
                    null -> {
                        // Se lanza el ActionMode.
                        cursor.moveToPosition(adapterPosition)
                        actionMode = it.startActionMode(actionModeCallback)
                        it.isSelected = true
                        true
                    }
                    else -> false
                }
                return@setOnLongClickListener true
            }
            noFavImageView.setOnClickListener()
            {
                cursor.moveToPosition(adapterPosition)
                var database = MyDBOpenHelper(context, null)
                database.marcarFavorito(cursor.getInt(0))
                noFavImageView.visibility = View.GONE
                favImageView.visibility = View.VISIBLE
            }
        }


        /**
         * Modo de acción contextual.
         */
        private val actionModeCallback = object : ActionMode.Callback {
            // Método llamado al selección una opción del menú.
            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?)
                    : Boolean {

                return when (item!!.itemId) {
                    R.id.optionDelete -> {
                        val builder = AlertDialog.Builder(context)
                        builder.setTitle("Confirmación")
                        builder.setMessage("¿Seguro que quieres borrar el juego?")
                        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                            val db = MyDBOpenHelper(context, null)
                            db.delJuego(cursor.getInt(0))
                            cursor = db.readableDatabase.rawQuery(
                                "SELECT * FROM juegos;", null
                            )
                            notifyItemRemoved(adapterPosition)
                            notifyItemRangeChanged(adapterPosition, getItemCount())
                        }
                        builder.setNegativeButton(android.R.string.no) { dialog, which ->
                            Toast.makeText(
                                context,
                                android.R.string.no, Toast.LENGTH_SHORT
                            ).show()
                        }
                        builder.show()
                        mode!!.finish()
                        return true
                    }
                    R.id.optionEdit -> {
                        val intent = Intent(context, DetallesActivity::class.java).apply {
                            putExtra("codigo", cursor.getString(0))
                            putExtra("nombre", cursor.getString(1))
                            putExtra("genero", cursor.getString(2))
                            putExtra("año", cursor.getString(3))
                            putExtra("compañia", cursor.getString(4))
                            putExtra("consola", cursor.getString(5))
                            putExtra("imagen", cursor.getString(6))
                        }
                        startActivity(context, intent, null)
                        return true
                    }
                    else -> false
                }
            }

            // Llamado cuando al crear el modo acción a través de startActionMode().
            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                //val inflater = mActivity?.menuInflater
                // Así no necesito la activity
                val inflater = mode?.menuInflater
                inflater?.inflate(R.menu.action_mode_menu, menu)
                return true
            }

            // Se llama cada vez que el modo acción se muestra, siempre
            // después de onCreateActionMode().
            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?)
                    : Boolean {
                return false
            }

            // Se llama cuando el usuario sale del modo de acción.
            override fun onDestroyActionMode(mode: ActionMode?) {
                actionMode = null
            }
        }
    }
}