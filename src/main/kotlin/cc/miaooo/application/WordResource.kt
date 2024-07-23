package cc.miaooo.application

import cc.miaooo.application.vo.WordDetailVo
import cc.miaooo.application.vo.WordSearchVo
import cc.miaooo.service.WordService
import jakarta.annotation.security.RolesAllowed
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType

@Path("/words")
@Produces(MediaType.APPLICATION_JSON)
class WordResource(
    val wordService: WordService
) {
    @GET
    @RolesAllowed("admin", "user")
    @Path("/{id}")
    fun detail(@PathParam("id") id: Long): WordDetailVo {
        return wordService.detail(id)
    }

    @GET
    @RolesAllowed("admin", "user")
    @Path("/search")
    fun search(@QueryParam("keywords") keywords: String): List<WordSearchVo> {
        return wordService.search(keywords)
    }
}