package com.testecreditas.simuladorcredito.domain.factory

import com.testecreditas.simuladorcredito.domain.model.SimulacaoCredito
import java.math.BigDecimal
import java.time.LocalDate
import java.time.Period

object SimulacaoCreditoFactory {

    fun criar(valor: BigDecimal, prazo: Int, dataNascimento: LocalDate): SimulacaoCredito {
        val idade = Period.between(dataNascimento, LocalDate.now()).years
        val taxaAnual = when {
            idade <= 25 -> BigDecimal("0.05")
            idade in 26..40 -> BigDecimal("0.03")
            idade in 41..60 -> BigDecimal("0.02")
            else -> BigDecimal("0.04")
        }

        return SimulacaoCredito(
            valor = valor,
            prazo = prazo,
            idade = idade,
            taxaAnual = taxaAnual
        )
    }
}
