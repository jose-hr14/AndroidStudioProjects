package com.joheri.practica01

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.joheri.practica01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = enlazarBinding()
        setContentView(binding.root)

        binding.calcularBoton.setOnClickListener()
        {
            esconderTeclado()
            if(binding.nombrePT.text.isNotEmpty() && binding.diaPT.text.isNotEmpty()
                && binding.mesPT.text.isNotEmpty() && binding.anyoPT.text.isNotEmpty()
                && binding.radioGroup.checkedRadioButtonId != -1)
            {
                val nuevaFecha = Fecha()
                var genero = "";

                nuevaFecha.dia = binding.diaPT.text.toString().toInt()
                nuevaFecha.mes = binding.mesPT.text.toString().toInt()
                nuevaFecha.anyo = binding.anyoPT.text.toString().toInt()

                if(binding.radioGroup.checkedRadioButtonId == binding.masculinoRB.id)
                {
                    genero = "M"
                }
                else if(binding.radioGroup.checkedRadioButtonId == binding.femeninoRB.id)
                {
                    genero = "F"
                }

                if(nuevaFecha.verificarFecha())
                {
                    val edad = nuevaFecha.calcularEdad()

                    binding.edadTV.text = edad.toString()
                    binding.edadTV.visibility = View.VISIBLE
                    binding.caracterSticaTV.text = devolverCaracteristica(edad, genero)
                    binding.caracterSticaTV.visibility = View.VISIBLE
                }
                else
                    mostrarToast("La fecha introducida no es correcta")

            }
            else
            {
                mostrarToast("No se han introducido todos los datos")
            }
        }
    }


    fun esconderTeclado()
    {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
    }
    fun enlazarBinding(): ActivityMainBinding
    {
        return ActivityMainBinding.inflate(layoutInflater)
    }
    fun mostrarToast(texto: String)
    {
        Toast.makeText(this,texto, Toast.LENGTH_SHORT).show()
    }
    fun devolverCaracteristica(edad: Int, genero: String): String
    {
        if(edad < 13)
        {
            if(genero == "M")
                return "Niño"
            else if(genero == "F")
                return "Niña"
        }
        else if(edad in 13..17)
        {
            return "Adolescente"
        }
        else if(edad in 18..64)
        {
            if(genero == "M")
                return "Hombre"
            else if(genero == "F")
                return "Mujer"
        }
        else if(edad >= 65)
        {
            if(genero == "M")
                return "Jubilado"
            else if(genero == "F")
                return "Jubilada"
        }
        return ""
    }
}