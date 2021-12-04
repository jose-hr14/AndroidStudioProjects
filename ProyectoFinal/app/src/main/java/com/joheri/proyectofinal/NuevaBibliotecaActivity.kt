package com.joheri.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.joheri.proyectofinal.databinding.ActivityBibliotecaBinding
import com.joheri.proyectofinal.databinding.ActivityNuevaBibliotecaBinding

class NuevaBibliotecaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNuevaBibliotecaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNuevaBibliotecaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager2 = binding.ViewPager2
        val adapter = ViewPager2Adapter(supportFragmentManager, lifecycle)

        adapter.addFragment(TodosJuegosFragment(), "Todos juegos")
        adapter.addFragment(FavoritosJuegosFragment(), "Juegos favoritos")

        viewPager2.adapter = adapter

        TabLayoutMediator(binding.tabLayout, viewPager2) { tab, position ->
            tab.text = adapter.getPageTitle(position)
        }.attach()
    }
}