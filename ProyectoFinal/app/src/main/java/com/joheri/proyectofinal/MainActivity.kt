package com.joheri.proyectofinal

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.joheri.proyectofinal.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var juegosDBHelper: MyDBOpenHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        juegosDBHelper = MyDBOpenHelper(this, null)

        val db = juegosDBHelper.readableDatabase
        val cursor: Cursor = db.rawQuery(
            "SELECT count(*) FROM juegos;", null
        )
        binding.caratulaET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                Glide.with(binding.root).load(binding.caratulaET.text.toString()).into(binding.caratulaIImageView)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        binding.anadirJuegoButton.setOnClickListener()
        {
            val juego = Juego(binding.nombreET.text.toString(), binding.generoET.text.toString(), binding.anyoET.text.toString().toInt(), binding.companiaET.text.toString(), binding.consolaET.text.toString(), binding.caratulaET.text.toString())
            juegosDBHelper.addJuego(juego)
            mostrarSnackBar("Juego añadido")
        }
        binding.bibliotecaButton.setOnClickListener()
        {
            val myIntent = Intent(this@MainActivity, NuevaBiblioteca::class.java)
            startActivity(myIntent)
        }
    }
    //Función que muestra con un snackbar el mensaje que se le pase como parámetro
    private fun mostrarSnackBar(mensaje: String) {
        Snackbar.make(binding.mainLayout, mensaje, Snackbar.LENGTH_LONG).show()
    }
    private fun getJuegos()
    {
        var juego = Juego("Pokémon Rojo", "RPG", 1998, "Nintendo", "Game Boy", "https://static.wikia.nocookie.net/espokemon/images/d/db/Car%C3%A1tula_de_Pok%C3%A9mon_Rojo.jpg/revision/latest/scale-to-width-down/456?cb=20160715095430")
        juegosDBHelper.addJuego(juego)
        juego = Juego("Pokémon Azul", "RPG", 1998, "Nintendo", "Game Boy", "https://static.wikia.nocookie.net/espokemon/images/9/9d/Car%C3%A1tula_de_Pok%C3%A9mon_Azul.jpg/revision/latest?cb=20160715095744")
        juegosDBHelper.addJuego(juego)
        juego = Juego("Pokémon Amarillo", "RPG", 1998, "Nintendo", "Game Boy", "https://static.wikia.nocookie.net/espokemon/images/9/95/Pok%C3%A9mon_Amarillo.png/revision/latest?cb=20160715100157")
        juegosDBHelper.addJuego(juego)
        juego = Juego("Pokémon Oro", "RPG", 1998, "Nintendo", "Game Boy", "https://static.wikia.nocookie.net/espokemon/images/6/6b/Pokemon_Edici%C3%B3n_Oro.jpg/revision/latest?cb=20160715092932")
        juegosDBHelper.addJuego(juego)
        juego = Juego("Pokémon Plata", "RPG", 1998, "Nintendo", "Game Boy", "https://static.wikia.nocookie.net/espokemon/images/7/73/Pokemon_Edici%C3%B3n_Plata.jpg/revision/latest?cb=20160715093037")
        juegosDBHelper.addJuego(juego)
        juego = Juego("Pokémon Cristal", "RPG", 1998, "Nintendo", "Game Boy", "https://static.wikia.nocookie.net/espokemon/images/3/3b/Pokemon_Edici%C3%B3n_Cristal.jpg/revision/latest?cb=20160715093139")
        juegosDBHelper.addJuego(juego)
    }
}