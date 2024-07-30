package cc.miaooo.application

import cc.miaooo.service.WordComment
import cc.miaooo.service.WordCommentService
import jakarta.annotation.security.RolesAllowed
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody

@Path("/comments")
@Produces(MediaType.APPLICATION_JSON)
class WordCommentResource(
    val wordCommentService: WordCommentService
) {
    @POST
    @RolesAllowed("admin", "user")
    fun append(@RequestBody wordComment: WordComment): WordComment {
        return wordCommentService.append(wordComment);
    }

    @DELETE
    @RolesAllowed("admin", "user")
    @Path("/{id}")
    fun delete(@PathParam("id") id: Long) {
        wordCommentService.delete(id)
    }

    @GET
    @RolesAllowed("admin", "user")
    @Path("/{id}")
    fun detail(@PathParam("id") id: Long): WordComment {
        return wordCommentService.detail(id)
    }
}