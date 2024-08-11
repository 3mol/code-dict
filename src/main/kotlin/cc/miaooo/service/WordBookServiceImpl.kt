package cc.miaooo.service

import cc.miaooo.application.WordBook2Resource
import cc.miaooo.domain.WordBook
import cc.miaooo.domain.WordBookItem
import cc.miaooo.infra.repository.WordBookItemRepository
import cc.miaooo.infra.repository.WordBookRepository
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import java.time.LocalDateTime

@ApplicationScoped
class WordBookServiceImpl(
    val wordBookRepository: WordBookRepository,
    val wordBookItemRepository: WordBookItemRepository
) : WordBookService {
    @Transactional
    override fun newWordBook(wordBook: WordBook2Resource.NewWordBookReq): WordBook {
        val newWordBook = WordBook(
            name = wordBook.name,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        wordBookRepository.persist(newWordBook)
        return newWordBook
    }

    @Transactional
    override fun appendToWordBook(wordBookId: Long, wordId: Long): WordBookItem {
        val item = WordBookItem(
            wordBookId = wordBookId,
            wordId = wordId,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        );
        wordBookItemRepository.persist(item)
        return item;
    }

    override fun detail(id: Long): WordBook {
        return wordBookRepository.findById(id)
    }

    @Transactional
    override fun removeWordBook(id: Long) {
        wordBookItemRepository.deleteById(id)
    }

    @Transactional
    override fun deleteWordBook(wordBookId: Long) {
        wordBookRepository.deleteById(wordBookId)
    }

    @Transactional
    override fun renameWordBook(wordBookId: Long, name: String): WordBook {
        wordBookRepository.update("name = ?1 where id = ?2", name, wordBookId)
        return wordBookRepository.findById(wordBookId)
    }

    override fun getAllWordBooks(): List<WordBook> {
        return wordBookRepository.listAll()
    }
}