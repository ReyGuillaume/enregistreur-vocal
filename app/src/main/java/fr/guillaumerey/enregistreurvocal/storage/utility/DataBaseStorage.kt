package fr.guillaumerey.enregistreurvocal.storage.utility

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log
import fr.guillaumerey.enregistreurvocal.model.Record

abstract class DataBaseStorage<T>(private val helper: SQLiteOpenHelper, private val table: String): Storage<T> {

    protected abstract fun objectToValues(obj: T): ContentValues
    protected abstract fun cursorToObject(cursor: Cursor): T

    override fun insert(obj: T): Int {
        return helper.writableDatabase.insert(table, null, objectToValues(obj)).toInt()
    }

    override fun size(): Int {
        return helper.readableDatabase.query(table,
            null,
            null,
            null,
            null,
            null,
            null,
            null).count
    }

    override fun find(id: Int): T? {
        var obj: T? = null
        val cursor = helper.readableDatabase.query(table,
            null,
            "${BaseColumns._ID} = ?",
            arrayOf("$id"),
            null,
            null,
            null,
            null)
        if (cursor.moveToNext()){
            obj = cursorToObject(cursor)
        }
        cursor.close()
        return obj
    }


    override fun findAll(): List<T> {
        var l : MutableList<T> = ArrayList<T>()
        var cursor :Cursor = helper.readableDatabase.query(table,
            null,
            null,
            null,
            null,
            null,
            null)
        if (cursor.moveToFirst()){
            do {
                l.add(cursorToObject(cursor))
            } while (cursor.moveToNext())
        }
        return l
    }

    override fun update(id: Int, obj: T) {
        helper.readableDatabase.update(table,objectToValues(obj),"${BaseColumns._ID}=${id}",arrayOf("$id"))
    }

    override fun delete(id: Int) {
        helper.readableDatabase.delete(table,"${BaseColumns._ID}=?", arrayOf("$id"))
    }
}
