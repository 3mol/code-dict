package cc.miaooo.application

import cc.miaooo.application.vo.WordDetailVo
import cc.miaooo.application.vo.WordSearchVo
import cc.miaooo.service.WordAiService
import cc.miaooo.service.WordService
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType

@Path("/words")
@Produces(MediaType.APPLICATION_JSON)
class WordResource(
    val wordService: WordService,
    val wordAiService: WordAiService
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
    @Path("/ai/explain")
    fun explain(@QueryParam("word") word: String): String {
        return wordAiService.explain(word)
    }
}