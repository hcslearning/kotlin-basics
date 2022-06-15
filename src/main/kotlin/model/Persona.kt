package model

// NO se recomienda usar data class cuando queremos escribir getters y setters
// es mejor agregar los equals(), hashcode() y otros de manera manual
// o alternativamente, generar las validaciones fuera de la clase

class Persona(nombre:String) {

    // ejemplo de propiedad ya seteada en el constructor principal
    var nombre = nombre
        get() = field
        set(value) {
            if(value.isBlank()) {
                throw Exception("El nombre NO puede estar vacío")
            } else {
                field = value
            }
        }

    var edad:Int = 0
        get() = field
        set(value) {
            field = if( value >= 0 ) value else throw Exception("La edad debe ser mayor a cero")
        }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        return "nombre=$nombre edad=$edad"
    }
}