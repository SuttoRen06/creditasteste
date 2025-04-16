package com.testecreditas.simuladorcredito.domain.model

import java.math.BigDecimal

data class SimulacaoCredito(
    val valor: BigDecimal,
    val prazo: Int,
    val idade: Int,
    val taxaAnual: BigDecimal
)