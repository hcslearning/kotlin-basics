fun separador() {
    println( "#".repeat(30) + "\n" )
}

// ####################################################################

val numeroDecimal = 10.5f
val representacionBinaria       = Integer.toBinaryString( numeroDecimal.toRawBits() )
val representacionBinariaStr    = String.format("%32s", representacionBinaria).replace(" ", "0")
println("Float a trabajar: $numeroDecimal \n\n")
// 01000001001010000000000000000000
println("Float en binario: $representacionBinariaStr \n")
separador()

// ####################################################################

// signo 1er bit
val signo = if (representacionBinariaStr[0] == '0') "+" else "-"
println( "Signo: $signo \n" )
separador()

// ####################################################################

// exponente - 127 (8 bits sgtes)
val exponenteBin = representacionBinariaStr.substring(1, 9)
val exponenteDec = exponenteBin.toInt(2)
val exponenteCalc = exponenteDec - 127
println( "Exponente binario = $exponenteBin" )
println( "Exponente decimal = $exponenteDec" )
println( "Exponente calculado (-127) = $exponenteCalc" )
separador()

// ####################################################################

// mantisa (23 bits)
val mantisaBin = representacionBinariaStr.substring(10, 32).padStart(23, '0')
// 01010000000000000000000
var mantisaDec = 1.0
for (i in 0 until mantisaBin.length) {
    if (mantisaBin[i] == '0') continue
    mantisaDec += mantisaBin[i].toString().toInt(2) * ( 1/Math.pow(2.0, (i+1).toDouble()) )
}
println("Mantisa Bin: $mantisaBin")
println("Mantisa Dec: $mantisaDec")
separador()

// ####################################################################

println("Fórmula final: mantisa * 2 ^ exponente")
val resultado = mantisaDec * Math.pow(2.0, exponenteCalc.toDouble())
println("Fórmula final: $mantisaDec * 2 ^ $exponenteCalc = $resultado")
separador()

