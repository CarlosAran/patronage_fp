package com.patronage.patronage.data.sqlite
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context) : SQLiteOpenHelper(context, "SQLiteDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        // Create tables
        db?.execSQL(
            """
            CREATE TABLE PreguntaBean (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                texto STRING DEFAULT NULL, 
                resp_1 STRING DEFAULT NULL, 
                resp_2 STRING DEFAULT NULL, 
                resp_3 STRING DEFAULT NULL, 
                resp_4 STRING DEFAULT NULL, 
                resp_correcta STRING DEFAULT NULL, 
                recompensa STRING DEFAULT NULL,
                leida INTEGER NOT NULL DEFAULT 0
            )
            """
        )
        db?.execSQL(
            """
            CREATE TABLE EventoBean (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                texto STRING DEFAULT NULL,
                tipo INTEGER NOT NULL DEFAULT 0,
                leido INTEGER NOT NULL DEFAULT 0
            )
            """
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Handle schema changes
        db?.execSQL("DROP TABLE IF EXISTS PreguntaBean")
        db?.execSQL("DROP TABLE IF EXISTS EventoBean")
        onCreate(db)
    }

    fun insertEvento(leido: Int, texto: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("leido", leido)
            put("texto", texto)
        }
        return db.insert("EventoBean", null, values)
    }
    fun getAllEventos(): List<Map<String, Any>> {
        val db = readableDatabase
        val cursor = db.query("EventoBean", null, null, null, null, null, null)
        val eventos = mutableListOf<Map<String, Any>>()

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val leido = cursor.getInt(cursor.getColumnIndexOrThrow("leido"))
            val texto = cursor.getString(cursor.getColumnIndexOrThrow("texto"))
            eventos.add(mapOf("id" to id, "leido" to leido, "texto" to texto))
        }
        cursor.close()
        return eventos
    }
    fun updateEvento(id: Int, leido: Int, texto: String): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("leido", leido)
            put("texto", texto)
        }
        return db.update("EventoBean", values, "id = ?", arrayOf(id.toString()))
    }
    fun deleteEvento(id: Int): Int {
        val db = writableDatabase
        return db.delete("EventoBean", "id = ?", arrayOf(id.toString()))
    }
    fun clearEventos(): Int {
        val db = writableDatabase
        return db.delete("EventoBean", null, null)
    }

}
