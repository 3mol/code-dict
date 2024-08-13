package cc.miaooo.service

import cc.miaooo.application.WordBookResource
import cc.miaooo.domain.WordBook
import cc.miaooo.domain.WordBookItem

interface WordBookService {
    fun newWordBook(wordBook: WordBookResource.NewWordBookReq): WordBook
    fun appendToWordBook(wordBookId: Long, wordId: Long): WordBookItem
    fun removeWordBook(itemId: Long)
    fun deleteWordBook(wordBookId: Long)
    fun renameWordBook(wordBookId: Long, name: String): WordBook
    fun getAllWordBooks(): List<WordBook>
    fun detail(id: Long): WordBook
    fun removeWordBook(wordBookId: Long, wordId: Long)
    fun detailItem(wordBookId: Long, wordId: Long): WordBookItem?
}
