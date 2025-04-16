package com.testecreditas.simuladorcredito.service

import com.testecreditas.simuladorcredito.api.request.SimuladorCreditoRequest
import com.testecreditas.simuladorcredito.api.response.SimuladorCreditoResponse
import com.testecreditas.simuladorcredito.domain.factory.SimulacaoCreditoFactory
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import kotlin.math.pow

@Service
class SimuladorCreditoService {

    fun simular(request: SimuladorCreditoRequest): SimuladorCreditoResponse {

        val simulacaoCredito = SimulacaoCreditoFactory.criar(
            valor = request.valor,
            prazo = request.prazo,
            dataNascimento = request.dataNascimento
        )

        val taxaMensal = simulacaoCredito.taxaAnual.divide(BigDecimal(12), MathContext.DECIMAL128)

        val valorParcela = calcularPMT(simulacaoCredito.valor, taxaMensal, simulacaoCredito.prazo)

        val valorTotal = valorParcela.multiply(BigDecimal(simulacaoCredito.prazo), MathContext.DECIMAL128)
        val totalJuros = valorTotal.subtract(simulacaoCredito.valor)

        return SimuladorCreditoResponse(
            valorTotal = valorTotal.setScale(2, RoundingMode.HALF_UP),
            valorParcelas = valorParcela.setScale(2, RoundingMode.HALF_UP),
            totalJuros = totalJuros.setScale(2, RoundingMode.HALF_UP)
        )
    }

    private fun calcularPMT(valor: BigDecimal, taxaMensal: BigDecimal, prazoMeses: Int): BigDecimal {
        val r = taxaMensal
        val n = prazoMeses.toDouble()

        // (1 + r)
        val umMaisTaxa = BigDecimal.ONE.add(r)

        // (1 + r)^-n
        val fatorDesconto = BigDecimal(umMaisTaxa.toDouble().pow(-n))

        // 1 - (1 + r)^-n
        val denominador = BigDecimal.ONE.subtract(fatorDesconto)

        // PMT = valor × (r / denominador)
        return valor.multiply(r).divide(denominador, MathContext.DECIMAL128)
    }
}