package fr.guillaumerey.enregistreurvocal.storage

import android.content.Context
import android.content.SharedPreferences
import fr.guillaumerey.enregistreurvocal.model.Record
import fr.guillaumerey.enregistreurvocal.storage.utility.Storage

object RecordStorage {

    private const val STORAGE = "storage"
    const val NONE = 0
    const val DATA_BASE = 1
    private const val DEFAULT = NONE

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("com.example.mykount.preferences", Context.MODE_PRIVATE)
    }

    fun getStorage(context: Context): Int {
        return getPreferences(context).getInt(STORAGE, DEFAULT)
    }

    fun setStorage(context: Context, prefStorage: Int) {
        getPreferences(context).edit().putInt(STORAGE,prefStorage).apply()
    }

    fun get(context: Context): Storage<Record> {
        lateinit var storage: Storage<Record>
        when (getStorage(context)) {
            NONE -> storage = RecordNoneStorage()
            DATA_BASE -> storage = RecordDatabase(context)
        }
        return storage
    }
}