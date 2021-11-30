package com.joheri.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.bumptech.glide.Glide
import com.joheri.proyectofinal.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var juegosDBHelper: MyDBOpenHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        juegosDBHelper = MyDBOpenHelper(this, null)
        var juego = Juego(1, "Pokémon Rojo", "RPG", 1998, "Nintendo", "Game Boy", "https://static.wikia.nocookie.net/espokemon/images/d/db/Car%C3%A1tula_de_Pok%C3%A9mon_Rojo.jpg/revision/latest?cb=20160715095430")
        //juegosDBHelper.addJuego(juego)

        binding.caratulaET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                Glide.with(binding.root).load(binding.caratulaET.text.toString()).into(binding.caratulaIImageView)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

/*        binding.caratulaET.setOnClickListener()
        {
            Glide.with(this)
                .load(binding.caratulaET.text.toString())
                .into(binding.caratulaIImageView)
        }*/

        binding.anadirJuegoButton.setOnClickListener()
        {
            var juego = Juego(1, binding.nombreET.text.toString(), binding.generoET.text.toString(), binding.anyoET.text.toString().toInt(), binding.companiaET.text.toString(), binding.consolaET.text.toString(), binding.caratulaET.text.toString())
            juegosDBHelper.addJuego(juego)
        }
        binding.bibliotecaButton.setOnClickListener()
        {
            val myIntent = Intent(this@MainActivity, BibliotecaActivity::class.java)
            startActivity(myIntent)
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