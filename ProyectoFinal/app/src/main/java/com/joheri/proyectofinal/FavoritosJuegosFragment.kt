package com.joheri.proyectofinal

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.joheri.proyectofinal.databinding.FragmentFavoritosJuegosBinding
import com.joheri.proyectofinal.databinding.FragmentTodosJuegosBinding

class FavoritosJuegosFragment : Fragment() {
    private lateinit var binding: FragmentFavoritosJuegosBinding
    private lateinit var juegosDB: MyDBOpenHelper
    private lateinit var db: SQLiteDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        juegosDB = MyDBOpenHelper(requireActivity(), null)
        binding = FragmentFavoritosJuegosBinding.inflate(inflater, container, false)

        setUpRecyclerView()

        return binding.root
    }

    fun setUpRecyclerView()
    {
        // Se instancia la BD en modo lectura y se crea la SELECT.
        db = juegosDB.readableDatabase
        var cursor = db.rawQuery("SELECT * FROM juegos WHERE favorito = 1;", null)
        // Se crea el adaptador con el resultado del cursor.
        val myRecyclerViewAdapter = MyRecyclerViewAdapter()
        myRecyclerViewAdapter.MyRecyclerViewAdapter(requireActivity(), cursor)
        // Montamos el RecyclerView.
        binding.myRecyclerview.setHasFixedSize(true)
        binding.myRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.myRecyclerview.adapter = myRecyclerViewAdapter
    }
    override fun onResume() {
        super.onResume()
        setUpRecyclerView()
    }
}