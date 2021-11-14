package com.joheri.proyectofinal

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class MyDBOpenHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    val TAG = "SQLite"

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "juegos.db"
        val TABLA_COMPANIAS = "compañias"
        val COMPANIAS_CODIGO = "codigo"
        val COMPANIAS_NOMBRE = "nombre"

        val TABLA_CONSOLAS = "consolas"
        val CONSOLAS_CODIGO = "codigo"
        val CONSOLAS_NOMBRE = "nombre"
        val CONSOLAS_ANYO = "año"
        val CONSOLAS_idCOMPANIA = "idCompañia"

        val TABLA_JUEGOS = "juegos"
        val JUEGOS_CODIGO = "codigo"
        val JUEGOS_NOMBRE = "nombre"
        val JUEGOS_ANYO = "año"
        val JUEGOS_idCOMPANIA = "idCompania"


    }

    /**
     * Este método es llamado cuando se crea la base por primera vez. Debe
     * producirse la creación de todas las tablas que formen la base de datos.
     */
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            val crearTablaCompañias = "CREATE TABLE $TABLA_COMPANIAS" +
                    "($COMPANIAS_CODIGO INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COMPANIAS_NOMBRE TEXT)"
            db!!.execSQL(crearTablaCompañias)
            val crearTablaConsolas = "CREATE TABLE $TABLA_CONSOLAS " +
                    "($CONSOLAS_CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$CONSOLAS_NOMBRE TEXT, " +
                    "$CONSOLAS_ANYO TEXT," +
                    "$CONSOLAS_idCOMPANIA INTEGER FOREIGN KEY" +
                    "REFERENCES $TABLA_COMPANIAS ($COMPANIAS_CODIGO)" +
                    "ON DELETE CASCADE" +
                    "ON UPDATE NO ACTION"
            db!!.execSQL(crearTablaConsolas)
            val crearTablaJuegos = "CREATE TABLE $TABLA_JUEGOS " +
                    "($JUEGOS_CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$JUEGOS_NOMBRE TEXT," +
                    "$JUEGOS_ANYO INTEGER," +
                    "$JUEGOS_idCOMPANIA INTEGER  FOREIGN KEY" +
                    "REFERENCES $TABLA_COMPANIAS ($COMPANIAS_CODIGO)" +
                    "ON DELETE CASCADE" +
                    "ON UPDATE NO ACTION)"
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
            val dropTablaCompañias = "DROP TABLE IF EXISTS compañias"
            db!!.execSQL(dropTablaCompañias)
            val dropTablaConsolas = "DROP TABLE IF EXISTS consolas"
            db!!.execSQL(dropTablaConsolas)
            val dropTablaJuegos = "DROP TABLE IF EXISTS juegos"
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
    /**
     * Método para añadir un amigo a la tabla amigos.
     */
    fun addCompania(compania: Compania) {
        // Se crea un ArrayMap<>() haciendo uso de ContentValues().
        val data = ContentValues()
        data.put(COMPANIAS_CODIGO, compania.codigo)
        data.put(COMPANIAS_NOMBRE, compania.nombre)

        // Se abre la BD en modo escritura.
        val db = this.writableDatabase
        db.insert(TABLA_COMPANIAS, null, data)
        db.close()
    }

    fun addConsola(consola: Consola) {
        // Se crea un ArrayMap<>() haciendo uso de ContentValues().
        val data = ContentValues()
        data.put(CONSOLAS_CODIGO, consola.codigo)
        data.put(CONSOLAS_NOMBRE, consola.nombre)
        data.put(CONSOLAS_ANYO, consola.anyo)
        data.put(CONSOLAS_idCOMPANIA, consola.idCompania)

        // Se abre la BD en modo escritura.
        val db = this.writableDatabase
        db.insert(TABLA_CONSOLAS, null, data)
        db.close()
    }

    fun addJuego(juego: Juego) {
        // Se crea un ArrayMap<>() haciendo uso de ContentValues().
        val data = ContentValues()
        data.put(JUEGOS_CODIGO, juego.codigo)
        data.put(JUEGOS_NOMBRE, juego.nombre)
        data.put(JUEGOS_ANYO, juego.anyo)
        data.put(JUEGOS_idCOMPANIA, juego.idCompania)

        // Se abre la BD en modo escritura.
        val db = this.writableDatabase
        db.insert(TABLA_JUEGOS, null, data)
        db.close()
    }
    /**
     * Método para eliminar un amigo de la tabla por el identificador.
     */
    fun delCompania(identifier: Int): Int {
        val args = arrayOf(identifier.toString())

        // Se abre la BD en modo escritura.
        val db = this.writableDatabase

        // Se puede elegir un sistema u otro.
        val result = db.delete(TABLA_COMPANIAS, "$COMPANIAS_CODIGO = $identifier", args)
        // db.execSQL("DELETE FROM $TABLA_AMIGOS WHERE $COLUMNA_ID = ?", args)

        db.close()
        return result
    }

    fun delConsola(identifier: Int): Int {
        val args = arrayOf(identifier.toString())

        // Se abre la BD en modo escritura.
        val db = this.writableDatabase

        // Se puede elegir un sistema u otro.
        val result = db.delete(TABLA_CONSOLAS, "$CONSOLAS_CODIGO = $identifier", args)
        // db.execSQL("DELETE FROM $TABLA_AMIGOS WHERE $COLUMNA_ID = ?", args)

        db.close()
        return result
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