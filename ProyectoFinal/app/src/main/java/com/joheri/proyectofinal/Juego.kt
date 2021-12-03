package com.joheri.proyectofinal

class Juego {
    var codigo: Int? = null
    var nombre: String? = null
    var genero: String? = null
    var anyo: Int? = null
    var compania: String? = null
    var consola: String? = null
    var caratulaURL: String? = null
    var favorito: Int? = null

    constructor(
        nombre: String?,
        genero: String?,
        anyo: Int?,
        compania: String?,
        consola: String?,
        caratulaURL: String?
    ) {
        this.nombre = nombre
        this.genero = genero
        this.anyo = anyo
        this.compania = compania
        this.consola = consola
        this.caratulaURL = caratulaURL
    }

    constructor(
        codigo: Int?,
        nombre: String?,
        genero: String?,
        anyo: Int?,
        compania: String?,
        consola: String?,
        caratulaURL: String?
    ) {
        this.codigo = codigo
        this.nombre = nombre
        this.genero = genero
        this.anyo = anyo
        this.compania = compania
        this.consola = consola
        this.caratulaURL = caratulaURL
    }
}
