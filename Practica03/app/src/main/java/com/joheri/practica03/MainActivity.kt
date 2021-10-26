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
class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    var isFragment01 = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager2 = binding.ViewPager2

        val adapter = ViewPager2Adapter(supportFragmentManager, lifecycle)

        adapter.addFragment(Fragment01(), "Cálculo")
        adapter.addFragment(Fragment02(), "Histórico")

        viewPager2.adapter = adapter

        TabLayoutMediator(binding.tabLayout, viewPager2){tab, position ->
            tab.text = adapter.getPageTitle(position)
        }.attach()
    }
    // Escribimos los datos separados por punto y coma en el fichero y lo guardamos
    // para posteriormente mostrarlo en el historial y dejarlos guardados. De esta
    // manera, aunque cerremos la aplicación, los datos del historial no se perderán.
    public fun escribirEnFichero(datos: String) {
        try {
            val writer = PrintWriter(openFileOutput(getString(R.string.filename), MODE_APPEND))
            writer.println(datos)
            writer.flush()
            writer.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}

/*

//Función para enlazar la vista con el código
        binding = enlazarBinding()
        setContentView(binding.root)

        binding.historicoBoton.setOnClickListener()
        {
            val intent = Intent(this, SecondActivity::class.java)

            startActivity(intent)
        }

        //Configuramos el evento que se desarrolla al pulsar el botón
        binding.calcularBoton.setOnClickListener()
        {
            esconderTeclado()

            //Llevamos a cabo la ejecución de las instrucciones solo cuando los campos estén llenos
            if(binding.nombrePT.text.isNotEmpty() && binding.diaPT.text.isNotEmpty()
                && binding.mesPT.text.isNotEmpty() && binding.anyoPT.text.isNotEmpty()
                && binding.radioGroup.checkedRadioButtonId != -1)
            {
                val nuevaFecha = Fecha()
                var genero = ""

                //Tomamos los valores de los plain text
                // y se los asignamos al objeto del tipo Fecha
                nuevaFecha.dia = binding.diaPT.text.toString().toInt()
                nuevaFecha.mes = binding.mesPT.text.toString().toInt()
                nuevaFecha.anyo = binding.anyoPT.text.toString().toInt()

                //Aquí controlamos si el radio buttón
                if(binding.radioGroup.checkedRadioButtonId == binding.masculinoRB.id)
                {
                    genero = getString(R.string.masculino)
                }

                //seleccionado es masculino o femenino
                else if(binding.radioGroup.checkedRadioButtonId == binding.femeninoRB.id)
                {
                    genero = getString(R.string.femenino)
                }

                //Verificamos la fecha antes de mostrar la edad
                if(nuevaFecha.verificarFecha())
                {
                    //Llamamos al método de la clase fecha para que nos calcule la edad
                    val edad = nuevaFecha.calcularEdad()
                    //Llamamos a la función que calcule y devuelva la característica de la persona
                    val caracteristica = devolverCaracteristica(edad, genero)

                    //Escribimos la edad en el textView
                    binding.edadTV.text = edad.toString()
                    //Hacemos visible el textView
                    binding.edadTV.visibility = View.VISIBLE
                    //Escribimos la característica en el textView
                    binding.caracterSticaTV.text = caracteristica
                    //Mostramos el textView con la característica
                    binding.caracterSticaTV.visibility = View.VISIBLE

                    escribirTextoFormateado(nuevaFecha.dia.toString(), mesNumeroACadeena
                        (nuevaFecha.mes.toString()), nuevaFecha.anyo.toString(),
                        binding.nombrePT.text.toString(), edad.toString(), genero)

                }
                //Si la fecha es incorrecta, avisamos al usuario
                else
                    mostrarToast(getString(R.string.fechaIncorrecta))

            }
            //Si alguno de los campos está vacío, avisamos al usuario
            else
                mostrarToast(getString(R.string.faltanDatos))

        }
    }

    fun mesNumeroACadeena(mes: String): String
    {
        when(mes)
        {
            "1" -> return "Enero"
            "2" -> return "Febrero"
            "3" -> return "Marzo"
            "4" -> return "Abril"
            "5" -> return "Mayo"
            "6" -> return "Junio"
            "7" -> return "Julio"
            "8" -> return "Agosto"
            "9" -> return "Septiembre"
            "10" -> return "Octubre"
            "11" -> return "Noviembre"
            "12" -> return "Diciembre"
            else -> return ""
        }
    }
    // Con esta función, separamos los datos de la persona por punto y coma, para posteriormente, extraer
    // toda la cadena dividiéndola en puntos y comas y meterla en una lista
    fun escribirTextoFormateado(dia: String, mes: String, anyo: String, nombre: String, edad:String, genero:String){
        val textoFichero: String = dia + ";" + mes + ";" + anyo + ";" + nombre + ";" + edad + ";" + genero
        escribirEnFichero(textoFichero)
    }

    // Escribimos los datos separados por punto y coma en el fichero y lo guardamos
    // para posteriormente mostrarlo en el historial y dejarlos guardados. De esta
    // manera, aunque cerremos la aplicación, los datos del historial no se perderán.
    fun escribirEnFichero(datos: String) {
        try {
            val writer = PrintWriter(openFileOutput(getString(R.string.filename), MODE_APPEND))
            writer.println(datos)
            writer.flush()
            writer.close()
            Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }

    //Función para esconder el teclado al pulsar el botón
    fun esconderTeclado()
    {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
    }

    //Función para enlazar la vista con el código
    fun enlazarBinding(): ActivityMainBinding
    {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    //Función para mostrar el toast
    fun mostrarToast(texto: String)
    {
        Toast.makeText(this,texto, Toast.LENGTH_SHORT).show()
    }

    //Función para devolver la característica de la persona
    fun devolverCaracteristica(edad: Int, genero: String): String
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
    private fun showFragmentOne() {
        // Se establece la transacción de fragments, necesarios para añadir,
        // quitar o reemplazar fragments.
        val transaction = supportFragmentManager.beginTransaction()
        // Se instancia el fragment a mostrar.
        val fragment = Fragment01()

        // Indicamos el elemento del layout donde haremos el cambio.
        transaction.replace(R.id.fragment_holder01, fragment)

        // Se establece valor a null para indicar que no se está interesado
        // en volver a ese fragment más tarde, en caso contrario,
        // se indicaría el nombre del fragment, por ejemplo fragment.TAG,
        // aprovechando la variable creada en la clase.
        transaction.addToBackStack(null)
        // Se muestra el fragment.
        transaction.commit()
        isFragment01 = true
    }

    private fun showFragmentTwo() {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = Fragment02()

        transaction.replace(R.id.fragment_holder02, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        isFragment01 = false
    }
 */