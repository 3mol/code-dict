package cc.miaooo.application

import cc.miaooo.application.vo.WordDetailVo
import cc.miaooo.application.vo.WordSearchVo
import cc.miaooo.service.WordAiService
import cc.miaooo.service.WordService
import dev.ai4j.openai4j.chat.ChatCompletionRequest
import dev.ai4j.openai4j.chat.ChatCompletionResponse
import io.quarkiverse.langchain4j.openai.OpenAiRestApi
import io.quarkiverse.langchain4j.openai.runtime.config.LangChain4jOpenAiConfig
import io.quarkus.rest.client.reactive.QuarkusRestClientBuilder
import io.smallrye.mutiny.Multi
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import org.jboss.resteasy.reactive.RestStreamElementType
import java.net.URI
import java.net.URISyntaxException
import java.util.*


@Path("/words")
@Produces(MediaType.APPLICATION_JSON)
class WordResource(
    val wordService: WordService,
    val wordAiService: WordAiService,
    val runtimeConfig: LangChain4jOpenAiConfig
) {
    @GET
    @Path("/{id}")
    fun detail(@PathParam("id") id: Long): WordDetailVo {
        return wordService.detail(id)
    }

    @GET
    @Path("/detail")
    fun detail(@QueryParam("id") id: Long?, @QueryParam("word") word: String?): WordDetailVo {
        if (id == null && word == null) {
            return WordDetailVo()
        }
        if (id != null) {
            return wordService.detail(id)
        }
        if (word != null) {
            return wordService.detail(word)
        }
        return WordDetailVo()
    }

    @GET
    @Path("/search")
    fun search(@QueryParam("keywords") keywords: String): List<WordSearchVo> {
        return wordService.search(keywords)
    }

    @GET
    @Path("/random")
    fun random(
        @QueryParam("size") size: Int,
        @QueryParam("tag") tag: String = "cet4"
    ): List<WordSearchVo> {
        return wordService.random(size, tag)
    }

    @GET
    @Path("/ai/explain")
    fun explain(@QueryParam("word") word: String): String {
        return wordAiService.explain(word)
    }

    @GET
    @Path("/chat/streaming")
    @RestStreamElementType(MediaType.TEXT_PLAIN)
    @Throws(URISyntaxException::class)
    fun chatStreaming(@QueryParam("word") word: String): Multi<String> {
        val restApi = QuarkusRestClientBuilder.newBuilder()
            .baseUri(URI(runtimeConfig.defaultConfig().baseUrl()))
            .build(OpenAiRestApi::class.java)
        return restApi.streamingChatCompletion(
            createChatCompletionRequest("你是一名优秀英语翻译官，请帮助用户翻译和解释单词。这部分是用户期望得到解释的词语：```\n${word}\n```"),
            OpenAiRestApi.ApiMetadata.builder()
                .openAiApiKey(runtimeConfig.defaultConfig().apiKey())
                .build()
        )
            .map { r: ChatCompletionResponse ->
                if (r.choices() != null) {
                    if (r.choices().size == 1) {
                        val choice = r.choices()[0]
                        if (choice.finishReason() != null && choice.finishReason() == "stop") {
                            return@map "@END@";
                        }
                        val delta = choice.delta()
                        if (delta != null) {
                            if (delta.content() != null) {
                                return@map delta.content().replace("\n", "@NEWLINE@")
                            }
                        } else { // normally this is not needed but mock APIs don't really work with the streaming response
                            val message = choice.message()
                            if (message != null) {
                                val content = message.content()
                                if (content != null) {
                                    return@map content
                                }
                            }
                        }
                    }
                }
                ""
            }
    }

    fun createChatCompletionRequest(userMessage: String?): ChatCompletionRequest {
        return ChatCompletionRequest.builder()
            .model("gpt-4o-mini")
            .logitBias(Collections.emptyMap())
            .maxTokens(4096)
            .user("user")
            .presencePenalty(0.0)
            .frequencyPenalty(0.0)
            .addUserMessage(userMessage).build()
    }
}