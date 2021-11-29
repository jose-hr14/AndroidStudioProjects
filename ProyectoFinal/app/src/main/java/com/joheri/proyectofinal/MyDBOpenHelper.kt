package com.joheri.proyectofinal

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class MyDBOpenHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    val TAG = "SQLite"

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "juegos.db"
        val TABLA_JUEGOS = "juegos"
        val JUEGOS_CODIGO = "codigo"
        val JUEGOS_NOMBRE = "nombre"
        val JUEGOS_GENERO = "genero"
        val JUEGOS_ANYO = "año"
        val JUEGOS_COMPANIA = "compania"
        val JUEGOS_CONSOLA = "consola"
    }

    /**
     * Este método es llamado cuando se crea la base por primera vez. Debe
     * producirse la creación de todas las tablas que formen la base de datos.
     */
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            val crearTablaJuegos = "CREATE TABLE juegos (codigo INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, genero TEXT, año INTEGER, compañia INTEGER, consola TEXT, caratula_url TEXT)"
            db!!.execSQL(crearTablaJuegos)

/*            var juego = Juego(1, "Pokémon Rojo", "RPG", 1998, "Nintendo", "Game Boy")
            this.addJuego(juego)
            juego = Juego(1, "Pokémon Azul", "RPG", 1998, "Nintendo", "Game Boy")
            this.addJuego(juego)
            juego = Juego(1, "Pokémon Amarillo", "RPG", 1998, "Nintendo", "Game Boy")
            this.addJuego(juego)
            juego = Juego(1, "Pokémon Oro", "RPG", 1998, "Nintendo", "Game Boy")
            this.addJuego(juego)
            juego = Juego(1, "Pokémon Plata", "RPG", 1998, "Nintendo", "Game Boy")
            this.addJuego(juego)
            juego = Juego(1, "Pokémon Cristal", "RPG", 1998, "Nintendo", "Game Boy")
            this.addJuego(juego)*/
        } catch (e: SQLiteException) {
            e.printStackTrace()
        }
    }

    /**
     * Este método se invocará cuando la base de datos necesite ser actualizada.
     * Se utiliza para hacer DROPs, añadir tablas o cualquier acción que
     * actualice el esquema de la BD.
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        try {
            val dropTablaJuegos = "DROP TABLE IF EXISTS $TABLA_JUEGOS"
            db!!.execSQL(dropTablaJuegos)
            onCreate(db)
        } catch (e: SQLiteException) {
            e.printStackTrace()
        }
    }

    /**
     * Método opcional. Se llamará a este método después de abrir la base de
     * datos, antes de ello, comprobará si está en modo lectura. Se llama justo
     * después de establecer la conexión y crear el esquema.
     */
    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
    }

    fun addJuego(juego: Juego) {
        // Se crea un ArrayMap<>() haciendo uso de ContentValues().
        val data = ContentValues()
        //data.put(JUEGOS_CODIGO, juego.codigo)
        data.put("nombre", juego.nombre)
        data.put("genero", juego.genero)
        data.put("año", juego.anyo)
        data.put("compañia", juego.compania)
        data.put("consola", juego.consola)
        data.put("caratula_url", juego.caratulaURL)
        //this.writableDatabase.execSQL("INSERT INTO juegos (nombre, genero, año, compañia, consola) VALUES('nombre', 'genero', 'año', 'compañia', 'cosola'"));
        // Se abre la BD en modo escritura.
        val db = this.writableDatabase
        db.insert(TABLA_JUEGOS, null, data)
        db.close()
    }

    fun delJuego(identifier: Int) {
        val args = arrayOf(identifier.toString())

        // Se abre la BD en modo escritura.
        val db = this.writableDatabase

        // Se puede elegir un sistema u otro.
        //val result = db.delete(TABLA_JUEGOS, "$JUEGOS_CODIGO = $identifier", args)
        db.execSQL("DELETE FROM juegos where codigo = $identifier")

        db.close()

    }
}