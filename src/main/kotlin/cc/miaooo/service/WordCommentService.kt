package cc.miaooo.service

data class WordComment(val id: Long, val content: String, val userId: Long, val wordId: Long) {}

interface WordCommentService {

    fun append(commentDetail: WordComment): WordComment

    fun detail(id: Long): WordComment

    fun delete(id: Long)
}
