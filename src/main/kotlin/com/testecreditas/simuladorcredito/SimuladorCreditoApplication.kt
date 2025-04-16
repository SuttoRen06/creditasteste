package com.testecreditas.simuladorcredito

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class SimuladorCreditoApplication

fun main(args: Array<String>) {
	runApplication<SimuladorCreditoApplication>(*args)
}