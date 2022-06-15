package usecase

import model.Persona

class CollectionObjectToString {
    fun main() {
        val personas = listOf<Persona>(Persona("Juan"), Persona("Pedro"), Persona("María"))

        val nombres = personas
            .map { it.nombre }
            .joinToString(",")
        println(nombres)
    }
}