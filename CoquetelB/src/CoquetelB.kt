//Aula 03, Com o padrão Decorator

interface Coquetel {
    val nome: String
    val preco: Double
}

class Bebida(override val nome: String, override val preco: Double): Coquetel {}

abstract class Adicional(open var coquetel: Coquetel): Coquetel {
    abstract val descricao: String
    abstract val custo: Double

    override val nome: String
        get() = "${coquetel.nome} + $descricao"
    override val preco: Double
        get() = coquetel.preco + custo

}

class AdicionaldeLimao(override var coquetel: Coquetel): Adicional(coquetel) {
    override val descricao: String = "Limão"
    override val custo: Double = 1.0
}

class AdicionaldeSal(override var coquetel: Coquetel): Adicional(coquetel) {
    override val descricao: String = "Sal"
    override val custo: Double = 0.5
}

class AdicionaldeRefrigerante(override var coquetel: Coquetel): Adicional(coquetel) {
    override val descricao: String = "Refrigerante"
    override val custo: Double = 3.0
}

class AdicionaldeSuco(override var coquetel: Coquetel): Adicional(coquetel) {
    override val descricao: String = "Suco"
    override val custo: Double = 4.0
}

class AdicionaldeLeiteCondensado(override var coquetel: Coquetel): Adicional(coquetel) {
    override val descricao: String = "LeiteCondensado"
    override val custo: Double = 2.0
}

class AdicionaldeGelo(override var coquetel: Coquetel): Adicional(coquetel) {
    override val descricao: String = "Gelo"
    override val custo: Double = 0.1
}

class AdicionaldeAcucar(override var coquetel: Coquetel): Adicional(coquetel) {
    override val descricao: String = "Açucar"
    override val custo: Double = 0.8
}

fun main(args: Array<String>) {
    //- Vodka + Suco + Gelo + Açúcar
    var c1: Coquetel = Bebida("Vodka",10.0)
    c1 = AdicionaldeSuco(c1)
    c1 = AdicionaldeGelo(c1)
    c1 = AdicionaldeAcucar(c1)

    //- Tequila + Limão + Sal
    var c2: Coquetel = AdicionaldeSal(AdicionaldeLimao(Bebida("Tequila",15.0)))

    //- Cachaça + Leite Condensado + Açúcar + Gelo
    var c3: Coquetel = AdicionaldeGelo(AdicionaldeAcucar(AdicionaldeLeiteCondensado(Bebida("Cachaça",5.0))))

    println("Coquetel: ${c1.nome} no valor de R$:${c1.preco}")
    println("Coquetel: ${c2.nome} no valor de R$:${c2.preco}")
    println("Coquetel: ${c3.nome} no valor de R$:${c3.preco}")
}