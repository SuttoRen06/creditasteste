package com.testecreditas.simuladorcredito.application.usecase

import com.testecreditas.simuladorcredito.api.request.SimuladorCreditoRequest
import com.testecreditas.simuladorcredito.api.response.SimuladorCreditoResponse
import com.testecreditas.simuladorcredito.service.SimuladorCreditoService
import org.springframework.stereotype.Component

@Component
class SimularCreditoUseCase(
    private val simuladorCreditoService: SimuladorCreditoService
) {

    fun executar(request: SimuladorCreditoRequest): SimuladorCreditoResponse {
        return simuladorCreditoService.simular(request)
    }
}