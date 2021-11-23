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
            val crearTablaJuegos = "CREATE TABLE $TABLA_JUEGOS " +
                    "($JUEGOS_CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$JUEGOS_NOMBRE TEXT," +
                    "$JUEGOS_GENERO TEXT," +
                    "$JUEGOS_ANYO INTEGER," +
                    "$JUEGOS_COMPANIA INTEGER," +
                    "$JUEGOS_CONSOLA TEXT"
            db!!.execSQL(crearTablaJuegos)
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
        data.put(JUEGOS_CODIGO, juego.codigo)
        data.put(JUEGOS_NOMBRE, juego.nombre)
        data.put(JUEGOS_GENERO, juego.genero)
        data.put(JUEGOS_ANYO, juego.anyo)
        data.put(JUEGOS_COMPANIA, juego.compania)
        data.put(JUEGOS_CONSOLA, juego.consola)

        // Se abre la BD en modo escritura.
        val db = this.writableDatabase
        db.insert(TABLA_JUEGOS, null, data)
        db.close()
    }

    fun delJuego(identifier: Int): Int {
        val args = arrayOf(identifier.toString())

        // Se abre la BD en modo escritura.
        val db = this.writableDatabase

        // Se puede elegir un sistema u otro.
        val result = db.delete(TABLA_JUEGOS, "$JUEGOS_CODIGO = $identifier", args)
        // db.execSQL("DELETE FROM $TABLA_AMIGOS WHERE $COLUMNA_ID = ?", args)

        db.close()
        return result
    }
}