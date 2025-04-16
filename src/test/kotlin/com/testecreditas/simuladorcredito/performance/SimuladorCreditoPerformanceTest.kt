import com.testecreditas.simuladorcredito.api.request.SimuladorCreditoRequest
import com.testecreditas.simuladorcredito.service.SimuladorCreditoService
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDate

class SimuladorCreditoPerformanceTest {

    private val service = SimuladorCreditoService()

    @Test
    fun `deve simular 10 mil emprestimos em tempo aceitavel`() {
        val inicio = System.currentTimeMillis()

        repeat(10_000) {
            val request = SimuladorCreditoRequest(
                valor = BigDecimal("5000.00"),
                dataNascimento = LocalDate.of(1995, 1, 1),
                prazo = 24
            )
            service.simular(request)
        }

        val fim = System.currentTimeMillis()
        val duracao = fim - inicio

        println("Tempo total: ${duracao}ms")
        assertTrue(duracao < 3000, "A simulação demorou mais do que o esperado")
    }
}