package fr.guillaumerey.enregistreurvocal.model

class Contrib(
    val id: Int,
    var nom: String,
    val prenom: String,
    val situation: String
) {

    companion object {
        const val ID = "id"
        const val NOM = "nom"
        const val PRENOM = "prenom"
        const val SITUATION = "situation"
    }
}