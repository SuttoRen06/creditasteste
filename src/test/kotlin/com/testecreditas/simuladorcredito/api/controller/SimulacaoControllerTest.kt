package com.testecreditas.simuladorcredito.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.testecreditas.simuladorcredito.api.request.SimuladorCreditoRequest
import com.testecreditas.simuladorcredito.api.response.SimuladorCreditoResponse
import com.testecreditas.simuladorcredito.application.usecase.SimularCreditoUseCase
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

import java.math.BigDecimal
import java.time.LocalDate

@WebMvcTest(SimulacaoController::class)
class SimulacaoControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var useCase: SimularCreditoUseCase

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    fun `deve retornar 200 OK com resposta correta`() {
        val request = SimuladorCreditoRequest(
            valor = BigDecimal("2000.00"),
            dataNascimento = LocalDate.of(2002, 4, 10),
            prazo = 12
        )

        val response = SimuladorCreditoResponse(
            valorTotal = BigDecimal("2054.52"),
            valorParcelas = BigDecimal("171.21"),
            totalJuros = BigDecimal("54.52")
        )

        `when`(useCase.executar(request)).thenReturn(response)

        mockMvc.post("/simulacoes") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andExpect {
            status { isOk() }
            jsonPath("$.valorTotal") { value("2054.52") }
            jsonPath("$.valorParcelas") { value("171.21") }
            jsonPath("$.totalJuros") { value("54.52") }
        }

        verify(useCase).executar(request)
    }

    @Test
    fun `deve retornar 400 quando valor do emprestimo for nulo`() {
        val payload = """
        {
            "dataNascimento": "2000-01-01",
            "prazo": 12
        }
    """.trimIndent()

        mockMvc.post("/simulacoes") {
            contentType = MediaType.APPLICATION_JSON
            content = payload
        }.andExpect {
            status { isBadRequest() }
        }
    }

    @Test
    fun `deve retornar 400 quando data de nascimento for nula`() {
        val payload = """
        {
            "valor": 2000.00,
            "prazo": 12
        }
    """.trimIndent()

        mockMvc.post("/simulacoes") {
            contentType = MediaType.APPLICATION_JSON
            content = payload
        }.andExpect {
            status { isBadRequest() }
        }
    }

    @Test
    fun `deve retornar 400 quando prazo for menor que 1`() {
        val payload = """
        {
            "valor": 2000.00,
            "dataNascimento": "2000-01-01",
            "prazo": 0
        }
    """.trimIndent()

        mockMvc.post("/simulacoes") {
            contentType = MediaType.APPLICATION_JSON
            content = payload
        }.andExpect {
            status { isBadRequest() }
        }
    }


}