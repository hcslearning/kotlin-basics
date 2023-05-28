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

### Integer Numbers

```kotlin
val edad1:Long  = 35 // 64 bits | -2**63 - 2**63-1 | -9_223_372_036_854_775_808 - 9_223_372_036_854_775_807 
val edad2:Int   = 35 // 32 bits | -2**31 - 2**31-1 |             -2_147_483_648 - 2_147_483_647 
val edad3:Short = 35 // 16 bits | -2**15 - 2**15-1 |                    -32_768 - 32_767
val edad4:Byte  = 35 // 08 bits | -2**7 - 2**7-1   |                       -128 - 127
```

### Floating Point Numbers

```kotlin

```

#### IEEE754 32 bits (example)

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