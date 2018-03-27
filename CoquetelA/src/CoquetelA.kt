//Aula 03, Coquetel sem o padrão Decorator

interface Ingredient {
    val name: String
    val price: Double
}

class Additional(override val name: String, override val price: Double): Ingredient {}

class Drink(override val name: String, override val price: Double): Ingredient {}

class CockTail(val drink: Drink, val additionals: MutableList<Additional>) {
    fun description(): String {
        var partialDescription: String = drink.name
        additionals.forEach { additional -> partialDescription += " + ${additional.name}" }
        return partialDescription
    }

    fun cost(): Double {
        var partialCost: Double = drink.price
        additionals.forEach {additional -> partialCost += additional.price}
        return partialCost
    }

}

fun main(args: Array<String>) {
    val cachaca = Drink("Cachaça",5.0)
    val rum = Drink("Rum",20.0)
    val vodka = Drink("Vodka",10.0)
    val tequila = Drink("Tequila",15.0)

    val limao = Additional("Limão",1.0)
    val refrigerante = Additional("Refrigerante", 3.0)
    val suco = Additional("Suco",4.0)
    val leiteCondensado = Additional("Leite Condensado", 2.0)
    val gelo = Additional("Gelo",0.1)
    val acucar = Additional("Açúcar",0.8)
    val sal = Additional("Sal",0.5)

    //- Vodka + Suco + Gelo + Açúcar
    val c1 = CockTail(vodka, mutableListOf(suco,gelo,acucar))

    //- Tequila + Limão + Sal
    val c2 = CockTail(tequila, mutableListOf(limao,sal))

    //- Cachaça + Leite Condensado + Açúcar + Gelo
    val c3 = CockTail(cachaca, mutableListOf(leiteCondensado,acucar,gelo))

    c1.additionals.add(leiteCondensado)

    println("Coquetel: ${c1.description()} no valor de R$:${c1.cost()}")
    println("Coquetel: ${c2.description()} no valor de R$:${c2.cost()}")
    println("Coquetel: ${c3.description()} no valor de R$:${c3.cost()}")

}