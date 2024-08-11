package cc.miaooo.application

import cc.miaooo.domain.WordBook
import cc.miaooo.domain.WordBookItem
import cc.miaooo.service.WordBookService
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType


@Path("/word-books")
@Produces(MediaType.APPLICATION_JSON)
class WordBook2Resource(
    private val wordBookService: WordBookService
) {
    // select all word books
    @GET
    @Path("/all")
    fun getAllWordBooks(): List<WordBook> {
        return wordBookService.getAllWordBooks()
    }

    @GET
    @Path("/{id}")
    fun detail(@PathParam("id") id: Long): WordBook {
        return wordBookService.detail(id)
    }

    data class NewWordBookReq(val name: String)

    // new a word book
    @POST
    fun newWordBook(@QueryParam("name") name: String): WordBook {
        return wordBookService.newWordBook(NewWordBookReq(name))
    }

    // delete word book
    @DELETE
    @Path("/delete/{wordBookId}")
    fun deleteWordBook(@PathParam("wordBookId") wordBookId: Long) {
        wordBookService.deleteWordBook(wordBookId)
    }

    // rename word book
    @PUT
    @Path("/{wordBookId}/name/{name}")
    fun renameWordBook(
        @PathParam("wordBookId") wordBookId: Long,
        @PathParam("name") name: String
    ): WordBook {
        return wordBookService.renameWordBook(wordBookId, name)
    }
}

@Path("/word-book-items")
@Produces(MediaType.APPLICATION_JSON)
class WordBookItemResource(
    private val wordBookService: WordBookService
) {

    // append a word to word book;
    @POST
    @Path("/append/{wordBookId}/{wordId}")
    @Consumes(MediaType.APPLICATION_JSON)
    fun appendToWordBook(wordBookId: Long, wordId: Long): WordBookItem {
        return wordBookService.appendToWordBook(wordBookId, wordId)
    }

    // remove a word from word book
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    fun removeFromWordBook(@PathParam("id") id: Long) {
        wordBookService.removeWordBook(id)
    }
}