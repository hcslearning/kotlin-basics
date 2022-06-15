package usecase

import model.Persona

class CustomGetterSetter {

    fun main() {
        val p1 = Persona("Juan")
        //p1.edad = -1 // Genera Exception
        p1.edad = 50

        println( p1 )
    }

}