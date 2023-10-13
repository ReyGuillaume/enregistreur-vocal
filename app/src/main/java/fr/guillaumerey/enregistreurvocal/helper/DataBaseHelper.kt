package fr.guillaumerey.enregistreurvocal.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import fr.guillaumerey.enregistreurvocal.model.Record

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context,"mykount.db",null,1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE Expense (" +
                    "${BaseColumns._ID} INTEGER," +
                    "${Record.NAME} TEXT," +
                    "${Record.DATE} DATE," +
                    "${Record.TIME} INTEGER," +
                    "PRIMARY KEY(${BaseColumns._ID})" +
                    ")"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}