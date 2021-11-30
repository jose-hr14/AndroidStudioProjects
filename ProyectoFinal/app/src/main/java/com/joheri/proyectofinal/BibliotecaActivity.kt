package com.joheri.proyectofinal

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.joheri.proyectofinal.databinding.ActivityBibliotecaBinding

class BibliotecaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBibliotecaBinding
    private val juegosDB = MyDBOpenHelper(this, null)
    private lateinit var db: SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBibliotecaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle("Biblioteca juegos")

        setUpRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        setUpRecyclerView()
    }

    fun setUpRecyclerView()
    {
        // Se instancia la BD en modo lectura y se crea la SELECT.
        db = juegosDB.readableDatabase
        val cursor: Cursor = db.rawQuery(
            "SELECT * FROM juegos;", null
        )
        // Se crea el adaptador con el resultado del cursor.
        val myRecyclerViewAdapter = MyRecyclerViewAdapter()
        myRecyclerViewAdapter.MyRecyclerViewAdapter(this, cursor)
        // Montamos el RecyclerView.
        binding.myRecyclerview.setHasFixedSize(true)
        binding.myRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.myRecyclerview.adapter = myRecyclerViewAdapter
    }
}