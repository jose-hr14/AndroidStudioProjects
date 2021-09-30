package com.joheri.kotlinpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        kotlinPractice()

        var personita:Persona = Persona()

        personita.nombre
    }

    class Persona{
        var nombre:String = ""
        var apelido:String = ""
    }

    fun kotlinPractice(){

        var artist: String? = null

        val artista: String = "Hola me llamo Paco"

        for(a in artista)
        {
            Toast.makeText(this,artist, Toast.LENGTH_SHORT)
        }












    }
}