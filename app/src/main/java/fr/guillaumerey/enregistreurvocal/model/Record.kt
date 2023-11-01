package fr.guillaumerey.enregistreurvocal.model

class Record(
    val id: Int,
    var name: String,
    val date: String,
    val time: Int
) {
    companion object {
        const val ID = "id"
        const val NAME = "name"
        const val DATE = "date"
        const val TIME = "time"
    }

    fun newName(name : String){
        this.name = name
    }

    override fun toString(): String {
        return "id = ${id},name =${name}, date =${date}, time${time}"
    }
}