package fr.guillaumerey.enregistreurvocal.model

class Record(
    val id: Int,
    val name: String,
    val date: String,
    val time: Int
) {
    companion object {
        const val ID = "id"
        const val NAME = "name"
        const val DATE = "date"
        const val TIME = "time"
    }
}