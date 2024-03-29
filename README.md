# Kotlin Basics

## Install

1. You can install the compiler from https://github.com/JetBrains/kotlin/releases.
1. Alternatively, you can install an IDE like JetBrains or use Android Studio.


## Running Kotlin Code

### Using REPL (Read-Eval-Print Loop)

In the REPL, you can code directly in the CLI and immediately get the result.

```bash
kotlinc-jvm
```

### Using Kotlin like a scripting language 

```bash
kotlinc -script myscript.kts 
```


### Making a project 

```bash
gradle init  
```

## Basics 

### Variables 

```kotlin
// VAL works as a constant
val a = "hola"
a = "hola2"
// error: val cannot be reassigned

// VAR is mutable 
var b = "hola"
b = "hola2"
```

### Data Types 

#### Integer Numbers

```kotlin
val edad1:Long  = 35 // 64 bits | -2**63 - 2**63-1 | -9_223_372_036_854_775_808 - 9_223_372_036_854_775_807 
val edad2:Int   = 35 // 32 bits | -2**31 - 2**31-1 |             -2_147_483_648 - 2_147_483_647 
val edad3:Short = 35 // 16 bits | -2**15 - 2**15-1 |                    -32_768 - 32_767
val edad4:Byte  = 35 // 08 bits | -2**7 - 2**7-1   |                       -128 - 127
```

#### Floating Point Numbers

```kotlin
val peso:Double = 70.5  // MIN: 4.9E-324 | MAX: 1.7976931348623157E308
val peso2:Float = 70.5f // MIN: 1.4E-45  | MAX: 3.4028235E38

// Float MIN: 0.000000000000000000000000000000000000000000001401298464324817
System.out.printf( "%.100f", Float.MIN_VALUE)
// Float MAX: 340282346638528860000000000000000000000.0 
System.out.printf( "%.100f", Float.MAX_VALUE)

```

##### IEEE754 32 bits (example)

 1 bit  = sign (1 negative and 0 positive)  
 8 bits = exponent (+ or - 127)  
23 bits = mantissa  

```kotlin
val numeroDecimal = 10.5f
val representacionBinaria = Integer.toBinaryString( numeroDecimal.toRawBits() ).padStart(32, '0')
// 01000001001010000000000000000000

// signo = + (porque el primer dígito es 0)
val signo = if (representacionBinaria[0] == '0') "+" else "-"
 
// exponente - 127 (8 bits sgtes) 
// exponente = 130 - 127 = 3
val exponenteBin = representacionBinaria.substring(1, 9)
val exponenteDec = exponenteBin.toInt(2)
val exponenteCalc = exponenteDec - 127

// mantisa (23 bits)
val mantisaBin = representacionBinaria.substring(10, 32).padStart(23, '0')
// 01010000000000000000000
var mantisaDec = 1.0 // por fórmula parte en 1
for (i in 0 until mantisaBin.length) {
    if (mantisaBin[i] == '0') continue
    mantisaDec += mantisaBin[i].toString().toInt(2) * ( 1/Math.pow(2.0, (i+1).toDouble()) )
}

val resultado = mantisaDec * Math.pow(2.0, exponenteCalc.toDouble()) //10.5
```

#### Other Data Types 

```kotlin
val nombre:String = "Santiago"
val multiLine:String = """
Lorem ipsum
dolor asit atme
consequeum
"""
val letraC:Char = 'c' // 
val esDomingo = true // true or false
```

### Syntax for Long Numbers 

For readibility you can write using underscores:

```kotlin
val oneMillion  = 1_000_000  // integer
val colorHex    = 0xFF_00_CC // ej: new java.awt.Color(colorHex)
val dos         = 0b00000000_00000010 // 2 en bytes
```

### String Templates

```kotlin
val nombre = "Juan"
val mensaje = """
Querido $nombre,
Nos comunicamos con ud. por el motivo ...
El nombre con largo ${nombre.length}
"""
```

### Conditionals

#### if / else 

Traditionally, you can use if / else as a statement:
```kotlin
import java.time.LocalDate
import java.time.DayOfWeek

val hoy = LocalDate.now().dayOfWeek
if( hoy.equals(DayOfWeek.SUNDAY) ) {
    println("Hoy se descansa!!!")
} else if(hoy.equals(DayOfWeek.MONDAY)) {
    println("Se acabo el descanso :(")
} else {
    println("A trabajar!!!")
}
```

Kotlin have range syntax:

```kotlin
val personasEnFile = 51
if( personasEnFile in 0..50 ) {
 println("Cerrar y atender a todos")
} else {
 println("Cerrar y atender a las primeras 50, al resto entregar número para atención mañana.")
}
```

Kotlin doesn't hava a ternary operator, but you can use if / else as an expression.
```kotlin
var row = 2
val bg  = if(row%2 == 0) "#ccc" else "#fff"
```


#### when 

In the same way that **if/else**, **when** can be used as a statement and as a expression.

```kotlin
val personasEnFile = 51

// as a statement
when( personasEnFile ) {
 0           -> println("Cerrar")
 in 1..50    -> println("Cerrar y atender a todos")
 else        -> println("Cerrar y atender a las primeras 50, al resto entregar número para atención mañana.")
}

// as an expression
var mensaje = when( personasEnFile ) {
    0           -> "Cerrar"
    in 1..50    -> "Cerrar y atender a todos"
    else        -> "Cerrar y atender a las primeras 50, al resto entregar número para atención mañana."
}
```

### Loops

#### For Loop

```kotlin
val clientes = arrayOf("juan@123.cl", "ramon@123.cl", "gonzalo@123.cl")

// For in
for( email in clientes) {
    println("Enviando correo a ${email} \n")
}

// Using an index
for( (index, email) in clientes.withIndex() ) {
 println("Email cliente #${index+1} ${email} \n")
}
```

```kotlin
/* Steps and ranges */

for (i in 1..5) print(i) // 12345

for (i in 5 downTo 1) print(i) // 54321

for (i in 1..5 step 2) print(i) // 135

for (i in 'a'..'d') print(i) // abcd

for (i in 'a'..'d' step 2) print(i) // ac
```

#### While Loop

```kotlin
// while
var vueltas = 5
while(vueltas > 0) {
    println("quedan $vueltas vueltas \n")
    vueltas--
}

// do while
vueltas = 5
do {
   println("quedan $vueltas vueltas \n")
   vueltas--
} while (vueltas > 0)
```

#### Repeat Loop

```kotlin
repeat(2) {
    print("chao ")
}
// chao chao 
```

### Arrays and Collections 

#### Lists 

- List are **ordered** collections of elements 
- List elements can be accessed by **indices** 
- Elements can be **repeated** 

```kotlin
// listOf(...) -> inmutable 
val accesorios = listOf("fundas", "agua desmineralizada", "cobertor")
println(accesorios)

// mutableListOf(...) -> mutable 
val listaSupermercado = mutableListOf("arroz", "huevos", "sal")
listaSupermercado.remove("sal")
// Note: with a list defined with val, you can't change which list the variable
// refers to, but you can change the contents of the list 
```

#### Arrays 

- arrays store multiple items 
- arrays elements can be accessed through their indices 
- arrays elements are mutable 
- array size is fixed 

```kotlin
val pets = arrayOf("chola", "nano", "spike")
println(java.util.Arrays.toString(pets)) 
```

With an array defined with **val**, you can't change which array the variable refers to, 
but you can still change the contents of the array 


### Null Safety

- In Kotlin, variables cannot be null by default 
- You can explicitly assign a variable to null using the safe call operator (?)
- Allow null-pointer exceptions using the !! operator 
- You can test for null using the elvis (?:) operator 

#### Safe Call Operator (?) 

```kotlin
var cantidad: Int = null // error, las variables no pueden ser null por defecto
```

```kotlin
/* usando safe call operator (?) */
var cantidad: Int? = null // permite null
cantidad = cantidad?.dec() // decremento 

/* sin safe call operator*/
if( cantidad != null ) {
    cantidad = cantidad.dec() 
}
```

#### The !! operator 

- If you sure that a variable won't be null use !! operator 
- Then the compiler allow you call methods/properties on it 
- The !! can be throw a NullPointerException if the variable is null 
- Only use in extremely cases

```kotlin
val nullableValue: String? = getValueFromExternalSource()
val nonNullValue: String = nullableValue!! // Assuming you are certain that nullableValue is not null
```

```kotlin
val javaObject: JavaObject? = getJavaObject()
val nonNullValue: String = javaObject!!.getValue() // Assuming you are sure that getValue() never returns null
```

#### The Elvis operator (?:) 

```kotlin
var cantidad: Int? = null // null allowed variable
var mitad: Int = cantidad ?: 0 // whitout elvis op (?:) you get an error 
mitad /= 2 
print(mitad)
```

### Functions

- A block of code that performs a specific task 
- Breaks a large program into smaller modular chunks
- Declared using fun keyword
- Can take arguments with either named or default values 

```kotlin
fun saludar() {
    print("hola a todos!!!")
}
```

If a function doesn't return any useful value, its return type is Unit.

```kotlin
fun saludar(): Unit {
    print("hola a todos!!!")
}
```

Unit is a type with only one value: Unit.  
The Unit return type declaration is optional.

#### Parameters

Functions may have:

- Default parameters
- Required parameters
- Named arguments

###### Default Parameters

```kotlin
fun drive(spped: String = "fast") {
    println("driving ${speed}")
}
// ----------------------------------------------------
drive()                         // driving fast
drive("slow")                   // driving slow
drive(speed = "turtle-like")    // driving turtle-like 
```

###### Required Parameters

If not default is specified for a parameter, the corresponding argument is required.

```kotlin
fun calcularAreaRectangulo(largo:Int, ancho:Int):Int {
    return largo * ancho 
}
// ----------------------------------------------------
calcularAreaRectangulo(2)       // error: no value paased for .....
calcularAreaRectangulo(2, 3)    // 6
```

###### Named Arguments

To improve readibility, you can use named arguments.

```kotlin
calcularAreaRectangulo(largo = 2, ancho = 3)    // 6
```

It's considered good style to put default arguments after positional arguments, 
that way callers only have to specify the required arguments. 

#### Compact Functions

Compact functions, or single-expression functions, make your code more concise and readable.

```kotlin
// Complete version
fun calcularAreaRectangulo(largo:Int, ancho:Int):Int {
    return largo * ancho 
}
// -----------------------------------------------------
// Compact version
fun calcularAreaRectangulo(largo:Int, ancho:Int):Int = largo * ancho
```


##### Functions are first-class 

- Kotlin functions can be stored in variables and data structures 
- They can be passed as arguments to, and returned from, other higher-order functions 
- You can use hight-order functions to create new "built-in" functions 

###### Lambda functions 

A Lambda is an expression that makes a function that has no name 

```kotlin
val saludo: () -> Unit          = { println("Hola") }
val saludo2: (String) -> Unit   = { nombre -> println("Hola ${nombre}")}
val calcArea: (Int, Int) -> Int = { largo, ancho -> largo * ancho }
// --------------------------------------------------------------------------
saludo()        // Hola 
saludo2("Juan") // Hola Juan
calcArea(3,2)   // 6
```

Kotlin can infer the type:

```kotlin
val saludo2  = { nombre: String -> println("Hola ${nombre}")}
val calcArea = { largo:Int, ancho:Int -> largo * ancho }
// --------------------------------------------------------------------------
saludo2("Juan") // Hola Juan
calcArea(3,2)   // 6
```

###### Higher-order functions 

Higher-order functions take functions as parameters, or return a function.

```kotlin
val numeros = listOf(1, 2, 3)
val elDoble = numeros.map( {numero -> numero * 2} )
// when is the last parameter is not necesary parenthesis
val tambienDoble = numeros.map { numero -> numero * 2}
// when it's only one argument, you can use "it"
val tambienDoble2 = numeros.map { it * 2}
// either you can pass like a variable 
val elDobleFn: (Int) -> Int = { numero -> numero * 2 } 
val tambienDoble3 = numeros.map(elDobleFn)
// if it's not a Lambda you can use :: operator 
class Utils {
    companion object { // it's like a static member from other languages
        fun doble(numero:Int):Int = numero * 2 
    }
}
val tambienDoble4 = numeros.map(Utils::doble)
```

This type of functions is extremly useful when you work with lists.

```kotlin
val numeros = listOf(5, 10, 12, 20)
val pares   = numeros.filter { it % 2 == 0 }
```

```kotlin
val alumnos  = listOf("Juan", "Pedro", "Jonathan")
val alumnosJ = alumnos.filter { it[0] == 'J' }
```

###### Eager and Lazy Filters 

Evaluation of expressions in lists:

- **Eager**: occurs regardless of wheter the result is ever used.
- **Lazy**: occurs only if necessary at runtime 

**Note**: Lazy evaluation of lists is useful if you don't need the entire result, or if
if the list is exceptionally large and multiple copies wouldn't fit into RAM.  

Filters are eager by default. A new list is created each time you use a filter.
```kotlin
val alumnos  = listOf("Juan", "Pedro", "Jonathan")
val alumnosJ = alumnos.filter { it[0] == 'J' }
```

If you want make this **lazy** you need use a Sequence.
```kotlin
val alumnos  = listOf("Juan", "Pedro", "Jonathan")
val alumnosJ = alumnos.asSequence().filter { it[0] == 'J' }.toList()
```


