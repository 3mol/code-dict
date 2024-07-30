package cc.miaooo.service

import cc.miaooo.infra.repository.WordCommentPo
import cc.miaooo.infra.repository.WordCommentRepository
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional

@ApplicationScoped
class WordCommentServiceImpl(val wordCommentRepository: WordCommentRepository) :
    WordCommentService {

    @Transactional
    override fun append(commentDetail: WordComment): WordComment {
        val wordCommentPo = WordCommentPo(
            commentDetail.id, commentDetail.content, commentDetail.userId, commentDetail.wordId
        )
        wordCommentRepository.persist(wordCommentPo)
        return commentDetail.copy(id = wordCommentPo.id)
    }

    override fun detail(id: Long): WordComment {
        val wordCommentPo = wordCommentRepository.findById(id)
        return WordComment(
            wordCommentPo.id,
            wordCommentPo.content,
            wordCommentPo.userId,
            wordCommentPo.wordId
        )
    }

    override fun delete(id: Long) {
        wordCommentRepository.deleteById(id)
    }
}