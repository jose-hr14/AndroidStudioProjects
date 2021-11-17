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
        var compania = Compania(1, "Nintendo")
        juegosDBHelper.addCompania(compania)
    }
}