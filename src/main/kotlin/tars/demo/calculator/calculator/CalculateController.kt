package tars.demo.calculator.calculator

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CalculateController {
    @PostMapping("/calculate")
    fun calculate(
        @RequestBody body: CalculateRequest
    ): CalculateResponse {
        val result = Calculator.calculate(body.expression)
        return CalculateResponse(result)
    }

    data class CalculateResponse(val result: Double)
    data class CalculateRequest(val expression: String)
}