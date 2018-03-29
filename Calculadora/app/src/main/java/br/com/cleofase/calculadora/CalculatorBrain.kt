package br.com.cleofase.calculadora

/**
 * Created by cleofase on 27/03/18.
 */

sealed class Operation
class Constant(val algorithm: Double): Operation()
class Unary(val algorithm: (Double) -> Double): Operation()
class Binary(val algorithm: (Double, Double) -> Double): Operation()
class Equals(): Operation()
class Reset(): Operation()

class PendingBinaryOperation(val operation: (Double, Double) -> Double, val firstOperand: Double) {
    fun perform(secondOperand: Double): Double = operation(firstOperand,secondOperand)
}

class CalculatorBrain {

    private var accumulator: Double = 0.0
    private val operations: HashMap<String,Operation> = hashMapOf(
            "+" to Binary({a,b -> a+b}),
            "-" to Binary({a,b -> a-b}),
            "X" to Binary({a,b -> a*b}),
            "/" to Binary({a,b -> a/b}),
            "%" to Unary({ a -> a/100}),
            "=" to Equals(),
            "AC" to Reset()
            )
    private var pendingBinaryOperation: PendingBinaryOperation? = null


    fun setOperand(operand: Double) {
        accumulator = operand
    }

    fun performOperation(operationSymbol: String) {
        val operation = operations[operationSymbol]
        when(operation){
            is Constant -> accumulator = operation.algorithm
            is Unary -> accumulator = operation.algorithm(accumulator)
            is Binary -> pendingBinaryOperation = PendingBinaryOperation((operation.algorithm),accumulator)
            is Equals -> performPendingOperation()
            is Reset -> reset()
        }
    }

    private fun performPendingOperation() {
        accumulator = if (pendingBinaryOperation != null) {pendingBinaryOperation!!.perform(accumulator)} else {accumulator}
    }


    private fun reset() {
        accumulator = 0.0
        pendingBinaryOperation = null
    }

    val result: Double
        get() = accumulator

}
