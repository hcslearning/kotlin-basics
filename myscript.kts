import java.io.File

println("Listando los archivos de la carpeta actual...")

val path = "."
File(path).listFiles().forEach { println(it.name) }
