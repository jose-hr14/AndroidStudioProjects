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

        binding = enlazarBinding() //Función para enlazar la vista con el código
        setContentView(binding.root)

        binding.calcularBoton.setOnClickListener() //Configuramos el evento que se desarrolla al pulsar el botón
        {
            esconderTeclado()
            if(binding.nombrePT.text.isNotEmpty() && binding.diaPT.text.isNotEmpty() //Llevamos a cabo la ejecución de las
                && binding.mesPT.text.isNotEmpty() && binding.anyoPT.text.isNotEmpty() //instrucciones solo cuando los campos
                && binding.radioGroup.checkedRadioButtonId != -1) //estén llenos
            {
                val nuevaFecha = Fecha()
                var genero = "";

                nuevaFecha.dia = binding.diaPT.text.toString().toInt() //Tomamos los valores de los plain text
                nuevaFecha.mes = binding.mesPT.text.toString().toInt() // y se los asignamos al objeto del tipo Fecha
                nuevaFecha.anyo = binding.anyoPT.text.toString().toInt()

                if(binding.radioGroup.checkedRadioButtonId == binding.masculinoRB.id) //Aquí controlamos si el radio buttón
                {
                    genero = "M"
                }
                else if(binding.radioGroup.checkedRadioButtonId == binding.femeninoRB.id) //seleccionado es masculino o femenino
                {
                    genero = "F"
                }

                if(nuevaFecha.verificarFecha()) //Verificamos la fecha antes de mostrar la edad
                {
                    val edad = nuevaFecha.calcularEdad() //Llamamos al método de la clase fecha para que nos calcule la edad
                    val caracteristica = devolverCaracteristica(edad, genero) //Llamamos a la función que calcule y devuelva la característica de la persona

                    binding.edadTV.text = edad.toString() //Escribimos la edad en el textView
                    binding.edadTV.visibility = View.VISIBLE //Hacemos visible el textView
                    binding.caracterSticaTV.text = caracteristica //Escribimos la característica en el textView
                    binding.caracterSticaTV.visibility = View.VISIBLE  //Mostramos el textView con la característica
                }
                else //Si la fecha es incorrecta, avisamos al usuario
                    mostrarToast("La fecha introducida no es correcta")

            }
            else //Si alguno de los campos está vacío, avisamos al usuario
            {
                mostrarToast("No se han introducido todos los datos")
            }
        }
    }


    fun esconderTeclado() //Función para esconder el teclado al pulsar el botón
    {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
    }
    fun enlazarBinding(): ActivityMainBinding //Función para enlazar la vista con el código
    {
        return ActivityMainBinding.inflate(layoutInflater)
    }
    fun mostrarToast(texto: String) //Función para mostrar el toast
    {
        Toast.makeText(this,texto, Toast.LENGTH_SHORT).show()
    }
    fun devolverCaracteristica(edad: Int, genero: String): String //Función para devolver la característica de la persona
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