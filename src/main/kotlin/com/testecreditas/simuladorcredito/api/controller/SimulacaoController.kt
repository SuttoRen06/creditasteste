package com.testecreditas.simuladorcredito.api.controller

import com.testecreditas.simuladorcredito.api.request.SimuladorCreditoRequest
import com.testecreditas.simuladorcredito.api.response.SimuladorCreditoResponse
import com.testecreditas.simuladorcredito.application.usecase.SimularCreditoUseCase
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/simulacoes")
class SimulacaoController(
    private val simularCreditoUseCase: SimularCreditoUseCase
) {

    @PostMapping
    fun simular(@Valid @RequestBody request: SimuladorCreditoRequest): ResponseEntity<SimuladorCreditoResponse> {
        val resultado = simularCreditoUseCase.executar(request)
        return ResponseEntity.ok(resultado)
    }
}