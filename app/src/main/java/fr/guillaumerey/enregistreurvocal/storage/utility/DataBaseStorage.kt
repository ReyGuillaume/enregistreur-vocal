package fr.guillaumerey.enregistreurvocal.storage.utility

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import fr.guillaumerey.enregistreurvocal.model.Record

abstract class DataBaseStorage<T>(private val helper: SQLiteOpenHelper, private val table: String): Storage<T> {

    protected abstract fun objectToValues(obj: T): ContentValues
    protected abstract fun cursorToObject(cursor: Cursor): T

    override fun insert(obj: T): Int {
        return helper.writableDatabase.insert(table, null, objectToValues(obj)).toInt()
    }

    override fun size(): Int {
        return helper.readableDatabase.query(table, null, null, null, null, null,null,null).count
    }

    override fun find(id: Int): T {
        return cursorToObject(helper.readableDatabase.query(table,null,"${BaseColumns._ID}=${id}",null, null, null,null,null))
    }

    override fun findAll(): List<T> {
        var l : List<T> = emptyList()

        var cursor :Cursor = helper.readableDatabase.query(table,null,null,null,null,null,null)
        if (cursor.moveToFirst()){
            do {
                l.toMutableList().add(cursorToObject(cursor))
            }while (cursor.moveToNext())
        }
        return l
    }

    override fun update(id: Int, obj: T) {
        helper.readableDatabase.update(table,objectToValues(obj),"${BaseColumns._ID}=${id}",null)
    }

    override fun delete(id: Int) {
        helper.readableDatabase.delete(table,"${BaseColumns._ID}=${id}",null)
    }
}