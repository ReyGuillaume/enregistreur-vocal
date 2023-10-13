package fr.guillaumerey.enregistreurvocal.storage

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import fr.guillaumerey.enregistreurvocal.helper.DataBaseHelper
import fr.guillaumerey.enregistreurvocal.model.Record
import fr.guillaumerey.enregistreurvocal.storage.utility.DataBaseStorage

class RecordDatabase(context: Context) : DataBaseStorage<Record>(DataBaseHelper(context),"Record")  {

    companion object {
        const val ID = 0
        const val NAME = 1
        const val DATE = 2
        const val TIME = 3
    }
        override fun objectToValues(obj: Record): ContentValues {
            val values = ContentValues()
            values.put(Record.NAME,obj.name)
            values.put(Record.DATE,obj.date)
            values.put(Record.TIME,obj.time)
            return values
    }

    override fun cursorToObject(cursor: Cursor): Record {
        return Record(
            cursor.getInt(ID),
            cursor.getString(NAME),
            cursor.getString(DATE),
            cursor.getInt(TIME)
        )
    }
}