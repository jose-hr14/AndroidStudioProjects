package com.joheri.practica03

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.joheri.practica03.databinding.ActivityMainBinding
import java.io.*

//José Hernández Riquelme
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Enlazamos la vista con el código
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Preparamos el viewPager2 y el adapter del viewPager2
        val viewPager2 = binding.ViewPager2
        val adapter = ViewPager2Adapter(supportFragmentManager, lifecycle)

        //Añadimos los fragmentos al tabLayout, y le asignamos un título de pestaña
        //que identifique al fragmento
        adapter.addFragment(FragmentoCalculo(), "Cálculo")
        adapter.addFragment(FragmentoHistorico(), "Histórico")

        //Vinculamos el adapter del viewpager con el viewPager
        viewPager2.adapter = adapter

        //Cargamos las pestañas del labLayout en nuestra main activity
        TabLayoutMediator(binding.tabLayout, viewPager2) { tab, position ->
            tab.text = adapter.getPageTitle(position)
        }.attach()
    }
}