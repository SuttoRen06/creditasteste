package com.testecreditas.simuladorcredito.api.response

import java.math.BigDecimal

data class SimuladorCreditoResponse(
    val valorTotal: BigDecimal,
    val valorParcelas: BigDecimal,
    val totalJuros: BigDecimal
)