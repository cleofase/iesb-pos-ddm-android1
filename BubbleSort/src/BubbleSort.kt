// Aula 01, Exercício 01

fun main(args: Array<String>) {
    var numbers: Array<Int> = arrayOf(100,23,45,87,5,4,10,25,0,33,20,3,2,1)
    var initialTime: Long
    var finalTime: Long
    var totalTime: Float

    print("Entrada: ")
    numbers.forEach { number -> print("$number ") }
    initialTime = System.nanoTime()

    numbers = sort(numbers)

    finalTime = System.nanoTime()
    totalTime = (finalTime - initialTime).toFloat()/1000000
    print("\nSaída: ")
    numbers.forEach { number -> print("$number ") }
    println("\nTempo decorrido: ${totalTime}ms")
}

fun sort(numbers: Array<Int>): Array<Int> {
    val sortedNumbers = numbers

    for (pivot in 0..sortedNumbers.size-2) {
        for (curr in pivot + 1..sortedNumbers.size-1) {
            if (sortedNumbers[curr] < sortedNumbers[pivot]) {
                val swap = sortedNumbers[pivot]
                sortedNumbers[pivot] = sortedNumbers[curr]
                sortedNumbers[curr] = swap
            }
        }
    }

    return sortedNumbers
}