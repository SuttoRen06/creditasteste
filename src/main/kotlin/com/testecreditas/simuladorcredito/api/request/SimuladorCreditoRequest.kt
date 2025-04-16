package com.testecreditas.simuladorcredito.api.request

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDate

data class SimuladorCreditoRequest(

    @field:NotNull(message = "O valor do empréstimo é obrigatório")
    @field:DecimalMin(value = "1.00", message = "O valor deve ser positivo")
    val valor: BigDecimal,

    @field:NotNull(message = "A data de nascimento é obrigatória")
    val dataNascimento: LocalDate,

    @field:Min(value = 1, message = "Prazo deve ser maior que 0")
    val prazo: Int
)