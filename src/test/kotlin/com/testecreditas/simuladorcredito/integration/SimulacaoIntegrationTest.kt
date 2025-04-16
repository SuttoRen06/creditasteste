package com.testecreditas.simuladorcredito.integration

import com.testecreditas.simuladorcredito.api.request.SimuladorCreditoRequest
import com.testecreditas.simuladorcredito.api.response.SimuladorCreditoResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import java.math.BigDecimal
import java.time.LocalDate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SimulacaoIntegrationTest {

    @LocalServerPort
    private var port: Int = 0

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun `deve simular emprestimo com sucesso via API`() {
        val request = SimuladorCreditoRequest(
            valor = BigDecimal("1000.00"),
            dataNascimento = LocalDate.of(2000, 1, 1),
            prazo = 12
        )

        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
        }

        val response = restTemplate.exchange(
            "http://localhost:$port/simulacoes",
            HttpMethod.POST,
            HttpEntity(request, headers),
            SimuladorCreditoResponse::class.java
        )

        val body = response.body!!
        println("Resposta: $body")

        assertEquals(200, response.statusCode.value())
        assertEquals(3, listOf(body.valorTotal, body.valorParcelas, body.totalJuros).count { it != null })
    }
}