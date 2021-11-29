package com.joheri.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joheri.proyectofinal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var juegosDBHelper: MyDBOpenHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        juegosDBHelper = MyDBOpenHelper(this, null)
        var juego: Juego = Juego(1, "Pokémon Rojo", "RPG", 1998, "Nintendo", "Game Boy")
        juegosDBHelper.addJuego(juego)

        binding.anadirJuegoButton.setOnClickListener()
        {
            var juego = Juego(1, binding.nombreET.text.toString(), binding.generoET.text.toString(), binding.anyoET.text.toString().toInt(), binding.companiaET.text.toString(), binding.consolaET.text.toString())
            juegosDBHelper.addJuego(juego)
        }
        binding.button.setOnClickListener()
        {
            juegosDBHelper.readableDatabase
        }
        binding.button2.setOnClickListener()
        {
            juegosDBHelper.delJuego(3);
        }


    }
}