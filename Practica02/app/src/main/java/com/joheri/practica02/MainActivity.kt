package com.joheri.practica02

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.joheri.practica02.databinding.ActivityMainBinding
import java.io.*

//José Hernández Riquelme
class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = enlazarBinding() //Función para enlazar la vista con el código
        setContentView(binding.root)

        binding.historicoBoton.setOnClickListener()
        {
            val intent = Intent(this, SecondActivity::class.java)

            startActivity(intent)
        }

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
                    genero = getString(R.string.masculino)
                }
                else if(binding.radioGroup.checkedRadioButtonId == binding.femeninoRB.id) //seleccionado es masculino o femenino
                {
                    genero = getString(R.string.femenino)
                }

                if(nuevaFecha.verificarFecha()) //Verificamos la fecha antes de mostrar la edad
                {
                    val edad = nuevaFecha.calcularEdad() //Llamamos al método de la clase fecha para que nos calcule la edad
                    val caracteristica = devolverCaracteristica(edad, genero) //Llamamos a la función que calcule y devuelva la característica de la persona

                    binding.edadTV.text = edad.toString() //Escribimos la edad en el textView
                    binding.edadTV.visibility = View.VISIBLE //Hacemos visible el textView
                    binding.caracterSticaTV.text = caracteristica //Escribimos la característica en el textView
                    binding.caracterSticaTV.visibility = View.VISIBLE  //Mostramos el textView con la característica

                    crearFicheroTexto(nuevaFecha.dia.toString(), nuevaFecha.mes.toString(), nuevaFecha.anyo.toString(),binding.nombrePT.text.toString(), edad.toString(), genero)

                }
                else //Si la fecha es incorrecta, avisamos al usuario
                    mostrarToast(getString(R.string.fechaIncorrecta))

            }
            else //Si alguno de los campos está vacío, avisamos al usuario
            {
                mostrarToast(getString(R.string.faltanDatos))
            }
        }
    }

    fun crearFicheroTexto(dia: String, mes: String, anyo: String, nombre: String, edad:String, genero:String){
        var textoFichero: String
        textoFichero = dia + ";" + mes + ";" + anyo + ";" + nombre + ";" + edad + ";" + genero
        escribirEnFichero(textoFichero)
    }

    private fun escribirEnFichero(datos: String) {
        try {
            val writer: PrintWriter = PrintWriter(openFileOutput(getString(R.string.filename), MODE_APPEND))
            writer.println(datos)
            writer.flush()
            writer.close()
            Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun leerFichero() {
        var text = ""
        // Se comprueba si existe el fichero.
        if (fileList().contains(getString(R.string.filename))) {
            try {
                val entrada = InputStreamReader(openFileInput(getString(R.string.filename)))
                val br = BufferedReader(entrada)
                // Leemos la primera línea
                var linea = br.readLine()
                while (!linea.isNullOrEmpty()) {
                    // Obtenemos los datos separandolo por el ;
                    val datos: List<String> = linea.split(";")
                    val mostrar: String
                    // Montamos el texto a mostrar
                    // y lo añadimos al textView
                    mostrar = getString(R.string.articulo) + datos[0] +
                            getString(R.string.codigo) + datos[1] +
                            getString(R.string.precio) + datos[2]
                }
                br.close()
                entrada.close()
            } catch (e: IOException) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, getString(R.string.ficheroNoExiste), Toast.LENGTH_LONG).show()
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
            if(genero == getString(R.string.masculino))
                return getString(R.string.niño)
            else if(genero == getString(R.string.femenino))
                return getString(R.string.niña)
        }
        else if(edad in 13..17)
        {
            return getString(R.string.adolescente)
        }
        else if(edad in 18..64)
        {
            if(genero == getString(R.string.masculino))
                return getString(R.string.hombre)
            else if(genero == getString(R.string.femenino))
                return getString(R.string.mujer)
        }
        else if(edad >= 65)
        {
            if(genero == getString(R.string.masculino))
                return getString(R.string.jubilado)
            else if(genero == getString(R.string.femenino))
                return getString(R.string.jubilada)
        }
        return ""
    }
}