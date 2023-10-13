package fr.guillaumerey.enregistreurvocal.storage

import android.content.Context
import fr.guillaumerey.enregistreurvocal.model.Record
import fr.guillaumerey.enregistreurvocal.storage.utility.Storage

object RecordStorage {
    fun get(context: Context): Storage<Record> = RecordDatabase(context)
}