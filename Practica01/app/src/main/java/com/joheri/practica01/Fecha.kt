package com.joheri.practica01

import android.widget.Toast
import java.util.*

class Fecha() {

    var dia: Int = 1
    var mes: Int = 1
    var anyo: Int = 1

    constructor(dia:Int, mes:Int, anyo:Int) : this()
    {
        this.dia = dia
        this.mes = mes
        this.anyo = anyo
    }


    fun esBisiesto(): Boolean{
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

    fun verificarFecha():Boolean{
        if(this.dia in 1..31 && (this.mes == 1 || this.mes == 3 || this.mes == 4 || this.mes == 5 || this.mes == 7 || this.mes == 10 || this.mes == 12))
        {
            return true
        }
        else if(this.dia in 1..30 && (this.mes == 4 || this.mes == 6 || this.mes == 8 || this.mes == 11))
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

    fun calcularCumpleaños(): Int{
        dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - this.dia
        mes = Calendar.getInstance().get(Calendar.MONTH) + 1 - this.mes
        anyo = Calendar.getInstance().get(Calendar.YEAR) - this.anyo

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