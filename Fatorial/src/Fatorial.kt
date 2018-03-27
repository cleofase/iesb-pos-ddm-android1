// Aula 01, Exercício 04

fun main(vars: Array<String>) {
    print("Digite um número:")
    val number = readLine()!!.toInt()
    println("Fatorial de $number é: ${fact(number)}")
}

fun fact(n: Int): Int = if (n > 1) {n * fact(n-1)} else {1}