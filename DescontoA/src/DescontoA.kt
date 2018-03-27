// Aula 02, Strategy sem Interface

enum class CategoriaDoEstudante {
    ExAluno {
        override fun calcularDesconto(mensalidade: Double) = mensalidade * 0.2
    },
    AlunoNovo {
        override fun calcularDesconto(mensalidade: Double) = mensalidade * 0.0
    };

    abstract fun calcularDesconto(mensalidade: Double): Double
}

class Aluno(val nome: String, val mensalidade: Double, val type: CategoriaDoEstudante) {
    val fatura: Double
        get() = mensalidade - type.calcularDesconto(mensalidade)
}

fun main(args: Array<String>) {
    val sicrano = Aluno("Sicrano da Silva",1000.0,CategoriaDoEstudante.ExAluno)
    val fulano = Aluno("Fulano dos Santos",1000.0,CategoriaDoEstudante.AlunoNovo)

    println("${sicrano.nome} pagará R$${sicrano.fatura} no mês")
    println("${fulano.nome} pagará R$${fulano.fatura} no mês")
}