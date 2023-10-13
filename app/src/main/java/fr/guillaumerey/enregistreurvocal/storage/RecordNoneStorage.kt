package fr.guillaumerey.enregistreurvocal.storage

import fr.guillaumerey.enregistreurvocal.model.Record
import fr.guillaumerey.enregistreurvocal.storage.utility.Storage

class RecordNoneStorage ():Storage<Record> {
    override fun insert(obj: Record): Int {
        return -1
    }

    override fun size(): Int {
       return 0
    }

    override fun find(id: Int): Record? {
        return null
    }

    override fun findAll(): List<Record> {
        return emptyList()
    }

    override fun delete(id: Int) {}

    override fun update(id: Int, obj: Record) {}
}