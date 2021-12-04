package com.joheri.proyectofinal

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
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
        cursor.moveToNext()
        if(cursor.getInt(0) == 0)
        {
            getJuegos()
        }
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
            esconderTeclado()
            if(binding.nombreET.text.isNotEmpty() && binding.consolaET.text.isNotEmpty())
            {
                var anyo: Int? = null
                if(binding.anyoET.text.isNotEmpty())
                    anyo = binding.anyoET.text.toString().toInt()
                val juego = Juego(binding.nombreET.text.toString(), binding.generoET.text.toString(), anyo, binding.companiaET.text.toString(), binding.consolaET.text.toString(), binding.caratulaET.text.toString())
                juegosDBHelper.addJuego(juego)
                mostrarSnackBar("Juego añadido")
                binding.nombreET.setText("")
                binding.generoET.setText("")
                binding.anyoET.setText("")
                binding.companiaET.setText("")
                binding.consolaET.setText("")
                binding.generoET.setText("")
                binding.caratulaET.setText("")
            }
            else
            {
                mostrarSnackBar("Faltan al menos los campos de nombre y consola")
            }

        }
        binding.bibliotecaButton.setOnClickListener()
        {
            val myIntent = Intent(this@MainActivity, NuevaBibliotecaActivity::class.java)
            startActivity(myIntent)
        }
    }
    private fun mostrarSnackBar(mensaje: String) {
        Snackbar.make(binding.mainLayout, mensaje, Snackbar.LENGTH_LONG).show()
    }
    fun esconderTeclado() {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
    private fun getJuegos()
    {
        var juego = Juego("Pokémon Rojo", "RPG", 1999, "Nintendo", "Game Boy", "https://static.wikia.nocookie.net/espokemon/images/d/db/Car%C3%A1tula_de_Pok%C3%A9mon_Rojo.jpg/revision/latest/scale-to-width-down/456?cb=20160715095430")
        juegosDBHelper.addJuego(juego)
        juego = Juego("Pokémon Azul", "RPG", 1999, "Nintendo", "Game Boy", "https://static.wikia.nocookie.net/espokemon/images/9/9d/Car%C3%A1tula_de_Pok%C3%A9mon_Azul.jpg/revision/latest?cb=20160715095744")
        juegosDBHelper.addJuego(juego)
        juego = Juego("Pokémon Amarillo", "RPG", 2000, "Nintendo", "Game Boy", "https://static.wikia.nocookie.net/espokemon/images/9/95/Pok%C3%A9mon_Amarillo.png/revision/latest?cb=20160715100157")
        juegosDBHelper.addJuego(juego)
        juego = Juego("Pokémon Oro", "RPG", 2001, "Nintendo", "Game Boy Color", "https://static.wikia.nocookie.net/espokemon/images/6/6b/Pokemon_Edici%C3%B3n_Oro.jpg/revision/latest?cb=20160715092932")
        juegosDBHelper.addJuego(juego)
        juego = Juego("Pokémon Plata", "RPG", 2001, "Nintendo", "Game Boy Color", "https://static.wikia.nocookie.net/espokemon/images/7/73/Pokemon_Edici%C3%B3n_Plata.jpg/revision/latest?cb=20160715093037")
        juegosDBHelper.addJuego(juego)
        juego = Juego("Pokémon Cristal", "RPG", 2001, "Nintendo", "Game Boy Color", "https://static.wikia.nocookie.net/espokemon/images/3/3b/Pokemon_Edici%C3%B3n_Cristal.jpg/revision/latest?cb=20160715093139")
        juegosDBHelper.addJuego(juego)
        juego = Juego("Pokémon Rubí", "RPG", 2003, "Nintendo", "Game Boy Advance", "https://static.wikia.nocookie.net/espokemon/images/0/03/Car%C3%A1tula_de_Rub%C3%AD.png/revision/latest/scale-to-width-down/350?cb=20110508145005")
        juegosDBHelper.addJuego(juego)
        juego = Juego("Pokémon Zafiro", "RPG", 2003, "Nintendo", "Game Boy Advance", "https://static.wikia.nocookie.net/espokemon/images/d/d3/Car%C3%A1tula_de_Zafiro.png/revision/latest/scale-to-width-down/350?cb=20110508145255")
        juegosDBHelper.addJuego(juego)
        juego = Juego("Pokémon Rojo Fuego", "RPG", 2004, "Nintendo", "Game Boy Advance", "https://static.wikia.nocookie.net/espokemon/images/a/ac/Car%C3%A1tula_de_Rojo_Fuego.png/revision/latest/scale-to-width-down/350?cb=20190313061006")
        juegosDBHelper.addJuego(juego)
        juego = Juego("Pokémon Verde Hoja", "RPG", 2004, "Nintendo", "Game Boy Advance", "https://static.wikia.nocookie.net/espokemon/images/3/31/Car%C3%A1tula_de_Verde_Hoja.png/revision/latest/scale-to-width-down/350?cb=20140922113421")
        juegosDBHelper.addJuego(juego)
        juego = Juego("Pokémon Esmeralda", "RPG", 2005, "Nintendo", "Game Boy Advance", "https://static.wikia.nocookie.net/espokemon/images/0/02/Caratula_Esmeralda.jpg/revision/latest/scale-to-width-down/300?cb=20080314232122")
        juegosDBHelper.addJuego(juego)
        juego = Juego("Pokémon Diamante", "RPG", 2007, "Nintendo", "Nintendo DS", "https://static.wikia.nocookie.net/espokemon/images/9/94/Pok%C3%A9mon_Diamante.png/revision/latest/scale-to-width-down/400?cb=20120517045308")
        juegosDBHelper.addJuego(juego)
        juego = Juego("Pokémon Perla", "RPG", 2007, "Nintendo", "Nintendo DS", "https://static.wikia.nocookie.net/espokemon/images/6/64/Pok%C3%A9mon_Perla.png/revision/latest/scale-to-width-down/350?cb=20120517045246")
        juegosDBHelper.addJuego(juego)
        juego = Juego("Pokémon Platino", "RPG", 2009, "Nintendo", "Nintendo DS", "https://static.wikia.nocookie.net/espokemon/images/f/f4/Car%C3%A1tula_Pok%C3%A9mon_Platino_%28ESP%29.png/revision/latest/scale-to-width-down/350?cb=20130710194938")
        juegosDBHelper.addJuego(juego)
        juego = Juego("Pokémon Oro Heart Gold", "RPG", 2010, "Nintendo", "Nintendo DS", "https://static.wikia.nocookie.net/espokemon/images/a/a7/Pok%C3%A9mon_Edici%C3%B3n_Oro_HeartGold_car%C3%A1tula_ES.jpg/revision/latest/scale-to-width-down/220?cb=20140922113217")
        juegosDBHelper.addJuego(juego)
        juego = Juego("Pokémon Plata Soul Silver", "RPG", 2010, "Nintendo", "Nintendo DS", "https://static.wikia.nocookie.net/espokemon/images/5/5c/Pok%C3%A9mon_Edici%C3%B3n_Plata_SoulSilver_car%C3%A1tula_ES.jpg/revision/latest/scale-to-width-down/220?cb=20140922113206")
        juegosDBHelper.addJuego(juego)
    }
}