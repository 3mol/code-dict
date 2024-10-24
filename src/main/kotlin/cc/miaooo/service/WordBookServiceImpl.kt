package cc.miaooo.service

import cc.miaooo.application.WordBookResource
import cc.miaooo.domain.WordBook
import cc.miaooo.domain.WordBookItem
import cc.miaooo.infra.repository.WordBookItemRepository
import cc.miaooo.infra.repository.WordBookRepository
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrNull

@ApplicationScoped
class WordBookServiceImpl(
    val wordBookRepository: WordBookRepository,
    val wordBookItemRepository: WordBookItemRepository,
    val wordServiceImpl: WordServiceImpl
) : WordBookService {
    @Transactional
    override fun newWordBook(wordBook: WordBookResource.NewWordBookReq): WordBook {
        val newWordBook = WordBook(
            name = wordBook.name, createdAt = LocalDateTime.now(), updatedAt = LocalDateTime.now()
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
        val wordBook = wordBookRepository.findById(id)
        val wordBookItems = wordBookItemRepository.list("wordBookId = ?1", id)
        return wordBook.copy(items = wordBookItems.map { it.wordId })
    }

    override fun detailItem(wordBookId: Long, wordId: Long): WordBookItem? {
        val singleResultOptional =
            wordBookItemRepository.find("wordBookId = ?1 and wordId = ?2", wordBookId, wordId)
                .singleResultOptional<WordBookItem>()
        return singleResultOptional.getOrNull()
    }

    @Transactional
    override fun removeWordBook(wordBookId: Long, wordId: Long) {
        wordBookItemRepository.find("wordBookId = ?1 and wordId = ?2", wordBookId, wordId)
            .singleResultOptional<WordBookItem>().ifPresent {
                wordBookItemRepository.deleteById(it.id)
            }
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

    @Transactional
    override fun newWordBookByWords(
        wordBook: WordBookResource.NewWordBookReq, words: List<String>
    ): WordBook {
        val newWordBook = newWordBook(wordBook)
        words.map {
            val word = wordServiceImpl.detail(it)
            if (word.id == 0L) {
                println("匹配失败：$it")
                return@map 0L
            }else{
                println("匹配成功：$it")
            }
            return@map word.id
        }
            .filter { return@filter it != 0L }
            .forEach {
                this.appendToWordBook(newWordBook.id, it)
            }
        return newWordBook;
    }

    override fun getAllWordBooks(): List<WordBook> {
        return wordBookRepository.listAll()
    }
}