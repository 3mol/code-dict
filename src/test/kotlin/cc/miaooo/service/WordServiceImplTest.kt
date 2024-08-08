package cc.miaooo.service

import io.quarkus.test.junit.QuarkusIntegrationTest
import io.quarkus.test.junit.QuarkusTest
import jakarta.enterprise.inject.Default
import jakarta.inject.Inject
import org.junit.jupiter.api.Test


@QuarkusTest
class WordServiceImplTest {
    @Inject
    @field: Default
    lateinit var wordService: WordService

    @Test
    fun testPing() {
        val random = wordService.random(100)
        println(random)
    }
}