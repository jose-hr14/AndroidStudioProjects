package com.joheri.practica02

import java.util.*
//José Hernández Riquelme
class Fecha {

    var dia: Int = 1
    var mes: Int = 1
    var anyo: Int = 1

    //Función para devolver si un año es bisiesto
    private fun esBisiesto(): Boolean{
        if(anyo % 4 == 0)
        {
            if(anyo % 100 == 0)
            {
                if(anyo % 400 == 0)
                    return true
                else
                    return false
            }
            else
                return true
        }
        else
        {
            return false
        }
    }

    //Función para verificar la fecha. Se aprovecha de la función para verificar un año
    //bisiesto y así comprpbar si la fecha de febrero es correcta o no
    fun verificarFecha():Boolean{
        if(this.dia in 1..31 && (this.mes == 1 || this.mes == 3 || this.mes == 5 || this.mes == 7 || this.mes == 8 || this.mes == 10 || this.mes == 12))
        {
            return true
        }
        else if(this.dia in 1..30 && (this.mes == 4 || this.mes == 6  || this.mes == 9|| this.mes == 11))
        {
            return true
        }
        else if(this.dia in 1..29 && this.mes == 2 && this.esBisiesto())
        {
            return true
        }
        else if(this.dia in 1..28 && this.mes == 2  && !this.esBisiesto())
        {
            return true
        }

        return false
    }


    //Función para calcular la edad a raíz de la fecha del momento en el que se ejecuta la app
    fun calcularEdad(): Int{
        val fechaActual = Calendar.getInstance()
        var dia = fechaActual.get(Calendar.DAY_OF_MONTH) - this.dia
        var mes = fechaActual.get(Calendar.MONTH) + 1 - this.mes
        var anyo = fechaActual.get(Calendar.YEAR) - this.anyo

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
            return 0
        }
        return anyo
    }
}