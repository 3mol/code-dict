package cc.miaooo.application

import cc.miaooo.application.vo.WordDetailVo
import cc.miaooo.application.vo.WordSearchVo
import cc.miaooo.service.WordService
import jakarta.annotation.security.RolesAllowed
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
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
    @Path("/search/{keyword}")
    fun search(@PathParam("keyword") keyword: String): List<WordSearchVo> {
        return wordService.search(keyword)
    }
}