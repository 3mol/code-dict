package cc.miaooo.service

import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class WordAiService(
    val wordOpenAiService: WordOpenAiService,
    val localCacheService: LocalCacheService
) {

    fun explain(word: String): String {
        if (localCacheService.get(word).isNotEmpty()) {
            return localCacheService.get(word)
        }
        val aiMessageResponse = wordOpenAiService.explain(word)
        val resultText = aiMessageResponse?.content()?.text() ?: ""
        localCacheService.put(word, resultText)
        return resultText
    }
}