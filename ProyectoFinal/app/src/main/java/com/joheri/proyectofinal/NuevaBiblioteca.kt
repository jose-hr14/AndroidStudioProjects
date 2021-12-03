package com.joheri.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.joheri.proyectofinal.databinding.ActivityBibliotecaBinding
import com.joheri.proyectofinal.databinding.ActivityNuevaBibliotecaBinding

class NuevaBiblioteca : AppCompatActivity() {
    private lateinit var binding: ActivityNuevaBibliotecaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Enlazamos la vista con el código
        binding = ActivityNuevaBibliotecaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Preparamos el viewPager2 y el adapter del viewPager2
        val viewPager2 = binding.ViewPager2
        val adapter = ViewPager2Adapter(supportFragmentManager, lifecycle)

        //Añadimos los fragmentos al tabLayout, y le asignamos un título de pestaña
        //que identifique al fragmento
        adapter.addFragment(TodosJuegosFragment(), "Todos juegos")
        adapter.addFragment(FavoritosJuegosFragment(), "Juegos favoritos")

        //Vinculamos el adapter del viewpager con el viewPager
        viewPager2.adapter = adapter

        //Cargamos las pestañas del labLayout en nuestra main activity
        TabLayoutMediator(binding.tabLayout, viewPager2) { tab, position ->
            tab.text = adapter.getPageTitle(position)
        }.attach()
    }
}