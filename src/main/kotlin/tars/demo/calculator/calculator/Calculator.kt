package tars.demo.calculator.calculator

import java.util.*

object Calculator {

    fun calculate(
        expression: String,
    ): Double {
        val rpnExpression = toRPN(expression)
        return evaluateRPN(rpnExpression)
    }

    private fun toRPN(expression: String): String {
        val outputQueue = LinkedList<String>()
        val operatorStack = Stack<String>()
        val operators = hashMapOf("+" to 1, "-" to 1, "*" to 2, "/" to 2)

        // Adjusted regex to handle spaces and improved token recognition
        val regex = Regex("([0-9]+|[*+\\-/()]|\\s+)")

        for (token in regex.findAll(expression).map { it.value }) {
            when {
                token.isBlank() -> continue // Skip spaces
                token.isNumeric() -> outputQueue.add(token)
                token in operators -> {
                    while (operatorStack.isNotEmpty() && operators.getOrDefault(
                            operatorStack.peek(),
                            0
                        ) >= operators[token]!!
                    ) {
                        outputQueue.add(operatorStack.pop())
                    }
                    operatorStack.push(token)
                }

                token == "(" -> operatorStack.push(token)
                token == ")" -> {
                    while (operatorStack.isNotEmpty() && operatorStack.peek() != "(") {
                        outputQueue.add(operatorStack.pop())
                    }
                    operatorStack.pop()
                }
            }
        }

        while (operatorStack.isNotEmpty()) {
            outputQueue.add(operatorStack.pop())
        }

        return outputQueue.joinToString(" ")
    }

    private fun evaluateRPN(rpnExpression: String): Double {
        val stack = Stack<Double>()
        val tokens = rpnExpression.split(" ")

        for (token in tokens) {
            when {
                token.isNumeric() -> stack.push(token.toDouble())
                token in listOf("+", "-", "*", "/") -> {
                    val right = stack.pop()
                    val left = stack.pop()
                    val result = when (token) {
                        "+" -> left + right
                        "-" -> left - right
                        "*" -> left * right
                        "/" -> left / right
                        else -> throw IllegalArgumentException("Invalid operator")
                    }
                    stack.push(result)
                }
            }
        }

        return stack.pop()
    }

    private fun String.isNumeric(): Boolean = this.toDoubleOrNull() != null
}


