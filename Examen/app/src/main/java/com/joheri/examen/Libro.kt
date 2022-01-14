package com.joheri.examen

class Libro {
    var titulo: String? = null
    var autor: String? = null
    var formato: String? = null

    constructor(titulo: String?, autor: String?, formato: String?) {
        this.titulo = titulo
        this.autor = autor
        this.formato = formato
    }
}