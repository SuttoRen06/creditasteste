package com.testecreditas.simuladorcredito.domain.service

import com.testecreditas.simuladorcredito.api.request.SimuladorCreditoRequest
import com.testecreditas.simuladorcredito.service.SimuladorCreditoService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDate

class SimuladorCreditoServiceTest {

    private val service = SimuladorCreditoService()

    private fun assertBigDecimalIgualEsperado(
        esperado: BigDecimal,
        atual: BigDecimal
    ) {
        assertEquals(
            esperado.setScale(2),
            atual.setScale(2)
        )
    }

    @Test
    fun `deve aplicar taxa de 5 por cento para cliente com 22 anos`() {
        val nascimento = LocalDate.of(2002, 4, 10)
        val request = SimuladorCreditoRequest(
            valor = BigDecimal("2000.00"),
            dataNascimento = nascimento,
            prazo = 12
        )

        val response = service.simular(request)

        assertEquals(BigDecimal("2054.58").setScale(2), response.valorTotal.setScale(2))
        assertEquals(BigDecimal("171.21").setScale(2), response.valorParcelas.setScale(2))
        assertEquals(BigDecimal("54.58").setScale(2), response.totalJuros.setScale(2))
    }

    @Test
    fun `deve aplicar taxa de 4 por cento para cliente com 70 anos`() {
        val nascimento = LocalDate.of(1955, 4, 10)
        val request = SimuladorCreditoRequest(
            valor = BigDecimal("10000"),
            dataNascimento = nascimento,
            prazo = 12
        )

        val response = service.simular(request)

        assertBigDecimalIgualEsperado(BigDecimal("10217.99"), response.valorTotal)
        assertBigDecimalIgualEsperado(BigDecimal("851.50"), response.valorParcelas)
        assertBigDecimalIgualEsperado(BigDecimal("217.99"), response.totalJuros)
    }

    @Test
    fun `deve aplicar taxa de 3 por cento para cliente com 30 anos`() {
        val nascimento = LocalDate.of(1995, 4, 10)
        val request = SimuladorCreditoRequest(
            valor = BigDecimal("10000"),
            dataNascimento = nascimento,
            prazo = 12
        )

        val response = service.simular(request)

        assertBigDecimalIgualEsperado(BigDecimal("10163.24"), response.valorTotal)
        assertBigDecimalIgualEsperado(BigDecimal("846.94"), response.valorParcelas)
        assertBigDecimalIgualEsperado(BigDecimal("163.24"), response.totalJuros)
    }

    @Test
    fun `deve aplicar taxa de 2 por cento para cliente com 45 anos`() {
        val nascimento = LocalDate.of(1980, 4, 10)
        val request = SimuladorCreditoRequest(
            valor = BigDecimal("10000"),
            dataNascimento = nascimento,
            prazo = 12
        )

        val response = service.simular(request)

        assertBigDecimalIgualEsperado(BigDecimal("10108.66"), response.valorTotal)
        assertBigDecimalIgualEsperado(BigDecimal("842.39"), response.valorParcelas)
        assertBigDecimalIgualEsperado(BigDecimal("108.66"), response.totalJuros)
    }

    @Test
    fun `deve aplicar taxa de 2 por cento para cliente com 50 anos`() {
        val nascimento = LocalDate.of(1975, 4, 10)
        val request = SimuladorCreditoRequest(
            valor = BigDecimal("10000.00"),
            dataNascimento = nascimento,
            prazo = 5
        )

        val response = service.simular(request)

        assertEquals(BigDecimal("10050.06").setScale(2), response.valorTotal.setScale(2))
        assertEquals(BigDecimal("2010.01").setScale(2), response.valorParcelas.setScale(2))
        assertEquals(BigDecimal("50.06").setScale(2), response.totalJuros.setScale(2))
    }

}