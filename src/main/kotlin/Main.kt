import model.Persona

fun main(args: Array<String>) {
    val p1 = Persona("Juan")
    //p1.edad = -1 // Genera Exception
    p1.edad = 50

    println( p1 )
}