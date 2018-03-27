// Aula 01, Exercício 02 e 03

import kotlin.math.absoluteValue

fun main(vars: Array<String>) {
    print("Digite um número:")
    val number = readLine()!!.toInt()

    if (number%2 == 0) {
        drawOtherThing(number)
    } else {
        drawThing(number)
        drawOtherThing(number)

    }
}

fun drawThing(thingNum: Int) {
    val middle = 1+ thingNum/2

    for (x in 1..thingNum) {
        val dist = (middle - x).absoluteValue
        for (y in 1..thingNum) {
            print("${if ((y in 1..dist)||(y in (thingNum-dist+1)..thingNum)) {"  "} else {"* "}}")
        }
        println("")
    }

}

fun drawOtherThing(thingNum: Int) {
    for (y in 1..(2*thingNum)) {
        val deltaY = if (thingNum >= y) {y} else {2*thingNum - y + 1}
        for (x in 1..(2*thingNum)) {
            val deltaX = if (thingNum >= x) {x} else {2*thingNum - x + 1}
            print("${if(deltaX < deltaY) {deltaX} else {deltaY}} ")
        }
        println("")
    }

}