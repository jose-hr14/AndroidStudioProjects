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

        title = "Biblioteca juegos"

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
        var cursor: Cursor = db.rawQuery("SELECT count(*) FROM juegos;", null)
        cursor.moveToNext()
        var cantidad = cursor.getInt(0)
        if(cantidad == 0)
        {
            getJuegos()
        }
        db = juegosDB.readableDatabase
        cursor = db.rawQuery("SELECT * FROM juegos;", null)
        // Se crea el adaptador con el resultado del cursor.
        val myRecyclerViewAdapter = MyRecyclerViewAdapter()
        myRecyclerViewAdapter.MyRecyclerViewAdapter(this, cursor)
        // Montamos el RecyclerView.
        binding.myRecyclerview.setHasFixedSize(true)
        binding.myRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.myRecyclerview.adapter = myRecyclerViewAdapter
    }
    private fun getJuegos()
    {
        var juego = Juego(null, "Pokémon Rojo", "RPG", 1998, "Nintendo", "Game Boy", "https://static.wikia.nocookie.net/espokemon/images/d/db/Car%C3%A1tula_de_Pok%C3%A9mon_Rojo.jpg/revision/latest/scale-to-width-down/456?cb=20160715095430")
        juegosDB.addJuego(juego)
        juego = Juego(null, "Pokémon Azul", "RPG", 1998, "Nintendo", "Game Boy", "https://static.wikia.nocookie.net/espokemon/images/9/9d/Car%C3%A1tula_de_Pok%C3%A9mon_Azul.jpg/revision/latest?cb=20160715095744")
        juegosDB.addJuego(juego)
        juego = Juego(null, "Pokémon Amarillo", "RPG", 1998, "Nintendo", "Game Boy", "https://static.wikia.nocookie.net/espokemon/images/9/95/Pok%C3%A9mon_Amarillo.png/revision/latest?cb=20160715100157")
        juegosDB.addJuego(juego)
        juego = Juego(null, "Pokémon Oro", "RPG", 1998, "Nintendo", "Game Boy", "https://static.wikia.nocookie.net/espokemon/images/6/6b/Pokemon_Edici%C3%B3n_Oro.jpg/revision/latest?cb=20160715092932")
        juegosDB.addJuego(juego)
        juego = Juego(null, "Pokémon Plata", "RPG", 1998, "Nintendo", "Game Boy", "https://static.wikia.nocookie.net/espokemon/images/7/73/Pokemon_Edici%C3%B3n_Plata.jpg/revision/latest?cb=20160715093037")
        juegosDB.addJuego(juego)
        juego = Juego(null, "Pokémon Cristal", "RPG", 1998, "Nintendo", "Game Boy", "https://static.wikia.nocookie.net/espokemon/images/3/3b/Pokemon_Edici%C3%B3n_Cristal.jpg/revision/latest?cb=20160715093139")
        juegosDB.addJuego(juego)
    }
}