package cc.miaooo.service

import cc.miaooo.application.WordBookResource
import io.quarkus.test.junit.QuarkusTest
import jakarta.enterprise.inject.Default
import jakarta.inject.Inject
import org.junit.jupiter.api.Test

@QuarkusTest
class WordBookServiceImplTest {
    @Inject
    @field: Default
    lateinit var service: WordBookServiceImpl

    @Test
    fun newWordBookByWords() {
        service.newWordBookByWords(
            wordBook = WordBookResource.NewWordBookReq("高考单词"),
            listOf(
                "a",
                "abandon",
                "ability",
                "able",
                "abnormal",
                "aboard",
                "abolish",
                "abortion",
                "about",
                "above",
                "abroad",
                "abrupt",
                "absence",
                "absent",
                "absolute",
                "absorb",
                "abstract",
                "absurd",
                "abundant",
                "abuse",
                "academic",
                "academy",
                "accelerate",
                "accent",
                "accept",
                "access",
                "accessible",
                "accident",
                "accommodation",
                "accompany",
                "accomplish",
            )
        )
    }
}