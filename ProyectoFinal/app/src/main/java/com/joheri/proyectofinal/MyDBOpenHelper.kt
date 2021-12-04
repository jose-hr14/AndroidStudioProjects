package com.joheri.proyectofinal

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class MyDBOpenHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "juegos.db"
        val TABLA_JUEGOS = "juegos"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        try {
            val crearTablaJuegos = "CREATE TABLE juegos (codigo INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, genero TEXT, año INTEGER, compañia INTEGER, consola TEXT, caratula_url TEXT, favorito INTEGER);"
            db!!.execSQL(crearTablaJuegos)
        } catch (e: SQLiteException) {
            e.printStackTrace()
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        try {
            val dropTablaJuegos = "DROP TABLE IF EXISTS $TABLA_JUEGOS"
            db!!.execSQL(dropTablaJuegos)
            onCreate(db)
        } catch (e: SQLiteException) {
            e.printStackTrace()
        }
    }

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
    }

    fun addJuego(juego: Juego) {
        val data = ContentValues()
        data.put("nombre", juego.nombre)
        data.put("genero", juego.genero)
        data.put("año", juego.anyo)
        data.put("compañia", juego.compania)
        data.put("consola", juego.consola)
        data.put("caratula_url", juego.caratulaURL)
        data.put("favorito", 0)

        val db = this.writableDatabase
        db.insert(TABLA_JUEGOS, null, data)
        db.close()
    }

    fun delJuego(identifier: Int) {
        val args = arrayOf(identifier.toString())

        val db = this.writableDatabase

        db.execSQL("DELETE FROM juegos where codigo = $identifier")

        db.close()

    }

    fun updateJuego(identifier: Int, juego: Juego) {
        val args = arrayOf(identifier.toString())

        val data = ContentValues()
        data.put("nombre", juego.nombre)
        data.put("genero", juego.genero)
        data.put("año", juego.anyo)
        data.put("compañia", juego.compania)
        data.put("consola", juego.consola)
        data.put("caratula_url", juego.caratulaURL)

        val db = this.writableDatabase
        db.update("juegos", data, "codigo = ?", args)
        db.close()
    }

    fun marcarFavorito(identifier: Int) {
        val args = arrayOf(identifier.toString())

        val data = ContentValues()
        data.put("favorito", 1)

        val db = this.writableDatabase
        db.update("juegos", data, "codigo = ?", args)
        db.close()
    }

    fun desmarcarFavorito(identifier: Int) {
        val args = arrayOf(identifier.toString())

        val data = ContentValues()
        data.put("favorito", 0)

        val db = this.writableDatabase
        db.update("juegos", data, "codigo = ?", args)
        db.close()
    }
}