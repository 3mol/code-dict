package cc.miaooo.service

import cc.miaooo.application.WordBook2Resource
import cc.miaooo.domain.WordBook
import cc.miaooo.domain.WordBookItem

interface WordBookService {
    fun newWordBook(wordBook: WordBook2Resource.NewWordBookReq): WordBook
    fun appendToWordBook(wordBookId: Long, wordId: Long): WordBookItem
    fun removeWordBook(itemId: Long)
    fun deleteWordBook(wordBookId: Long)
    fun renameWordBook(wordBookId: Long, name: String): WordBook
    fun getAllWordBooks(): List<WordBook>
    fun detail(id: Long): WordBook
}
