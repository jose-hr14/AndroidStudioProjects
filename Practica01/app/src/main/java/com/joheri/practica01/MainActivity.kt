package com.joheri.practica01

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.joheri.practica01.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calcularBoton.setOnClickListener(){
            if(binding.diaPT.text.isNotEmpty() && binding.mesPT.text.isNotEmpty() && binding.anyoPT.text.isNotEmpty())
            {
                var dia:Int = binding.diaPT.text.toString().toInt()
                var mes:Int = binding.mesPT.text.toString().toInt()
                var anyo:Int = binding.anyoPT.text.toString().toInt()

                val nuevaFecha:Fecha = Fecha(dia, mes, anyo)

                if(nuevaFecha.verificarFecha())
                {
                    dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - nuevaFecha.dia
                    mes = Calendar.getInstance().get(Calendar.MONTH) + 1 - nuevaFecha.mes
                    anyo = Calendar.getInstance().get(Calendar.YEAR) - nuevaFecha.anyo

                    if(dia < 0)
                    {
                        mes -= 1
                        dia *= -1
                    }
                    if(mes < 0)
                    {
                        anyo -= 1
                        mes *= -1
                    }
                    if(anyo < 0)
                    {
                        Toast.makeText(this,"La fecha introducida supera la actual", Toast.LENGTH_SHORT).show()
                    }
                    binding.edadTV.text = anyo.toString()

                    binding.edadTV.visibility = View.VISIBLE

                }
                else
                {
                    Toast.makeText(this,"La fecha introducida no es correcta", Toast.LENGTH_SHORT).show()
                }




            }
            else
            {
                Toast.makeText(this, "Alguno de los valores no se ha introducido", Toast.LENGTH_LONG).show()
            }

        }

    }
}