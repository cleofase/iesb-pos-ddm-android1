//Aula 02, Strategy com Interface

interface Contribuinte {
    fun calcularDesconto(mensalidade: Double): Double
}

enum class TipoDeAluno: Contribuinte {
    ExAluno {
        override fun calcularDesconto(mensalidade: Double) = mensalidade * 0.2
    },
    AlunoNovo {
        override fun calcularDesconto(mensalidade: Double) = mensalidade * 0.0
    };
}

class Aluno(val name: String, val mensalidade: Double, val tipo: Contribuinte) {
    val fatura: Double
        get() = mensalidade - tipo.calcularDesconto(mensalidade)
}

fun main(args: Array<String>) {
    val sicrano = Aluno("Sicrano da Silva",1000.0,TipoDeAluno.ExAluno)
    val fulano = Aluno("Fulano dos Santos",1000.0,TipoDeAluno.AlunoNovo)
    println("${sicrano.name} pagará R$${sicrano.fatura} no mês")
    println("${fulano.name} pagará R$${fulano.fatura} no mês")
}