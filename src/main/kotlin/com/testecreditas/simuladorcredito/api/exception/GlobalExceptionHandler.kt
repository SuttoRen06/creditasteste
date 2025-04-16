package com.testecreditas.simuladorcredito.api.handler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

data class ErroCampo(val campo: String, val mensagem: String)
data class ErroValidacaoResponse(val erros: List<ErroCampo>)

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationError(ex: MethodArgumentNotValidException): ResponseEntity<ErroValidacaoResponse> {
        val erros = ex.bindingResult.fieldErrors.map {
            ErroCampo(it.field, it.defaultMessage ?: "Erro de validação")
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErroValidacaoResponse(erros))
    }
}