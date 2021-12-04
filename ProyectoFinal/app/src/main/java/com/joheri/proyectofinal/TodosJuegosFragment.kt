package com.joheri.proyectofinal

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.joheri.proyectofinal.databinding.FragmentTodosJuegosBinding

class TodosJuegosFragment : Fragment() {
    private lateinit var binding: FragmentTodosJuegosBinding
    private lateinit var juegosDB: MyDBOpenHelper
    private lateinit var db: SQLiteDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        juegosDB = MyDBOpenHelper(requireActivity(), null)
        binding = FragmentTodosJuegosBinding.inflate(inflater, container, false)

        setUpRecyclerView()

        return binding.root
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
        myRecyclerViewAdapter.MyRecyclerViewAdapter(requireActivity(), cursor, "SELECT * FROM juegos;")
        // Montamos el RecyclerView.
        binding.myRecyclerview.setHasFixedSize(true)
        binding.myRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.myRecyclerview.adapter = myRecyclerViewAdapter
    }
    private fun getJuegos()
    {
        var juego = Juego("Pokémon Rojo", "RPG", 1998, "Nintendo", "Game Boy", "https://static.wikia.nocookie.net/espokemon/images/d/db/Car%C3%A1tula_de_Pok%C3%A9mon_Rojo.jpg/revision/latest/scale-to-width-down/456?cb=20160715095430")
        juegosDB.addJuego(juego)
        juego = Juego( "Pokémon Azul", "RPG", 1998, "Nintendo", "Game Boy", "https://static.wikia.nocookie.net/espokemon/images/9/9d/Car%C3%A1tula_de_Pok%C3%A9mon_Azul.jpg/revision/latest?cb=20160715095744")
        juegosDB.addJuego(juego)
        juego = Juego( "Pokémon Amarillo", "RPG", 1998, "Nintendo", "Game Boy", "https://static.wikia.nocookie.net/espokemon/images/9/95/Pok%C3%A9mon_Amarillo.png/revision/latest?cb=20160715100157")
        juegosDB.addJuego(juego)
        juego = Juego( "Pokémon Oro", "RPG", 1998, "Nintendo", "Game Boy", "https://static.wikia.nocookie.net/espokemon/images/6/6b/Pokemon_Edici%C3%B3n_Oro.jpg/revision/latest?cb=20160715092932")
        juegosDB.addJuego(juego)
        juego = Juego( "Pokémon Plata", "RPG", 1998, "Nintendo", "Game Boy", "https://static.wikia.nocookie.net/espokemon/images/7/73/Pokemon_Edici%C3%B3n_Plata.jpg/revision/latest?cb=20160715093037")
        juegosDB.addJuego(juego)
        juego = Juego( "Pokémon Cristal", "RPG", 1998, "Nintendo", "Game Boy", "https://static.wikia.nocookie.net/espokemon/images/3/3b/Pokemon_Edici%C3%B3n_Cristal.jpg/revision/latest?cb=20160715093139")
        juegosDB.addJuego(juego)
    }
    override fun onResume() {
        super.onResume()
        setUpRecyclerView()
    }
}