package cc.miaooo.service

import cc.miaooo.application.WordBookResource
import io.quarkus.test.junit.QuarkusTest
import jakarta.enterprise.inject.Default
import jakarta.inject.Inject
import org.junit.jupiter.api.Test
import java.io.File

@QuarkusTest
class WordBookServiceImplTest {
    @Inject
    @field: Default
    lateinit var service: WordBookServiceImpl

    @Test
    fun newWordBookByWords() {
        val fileName = "src/test/resources/words-gaokao.txt" // 文件路径

        val wordList = mutableListOf<String>()

        File(fileName).forEachLine {
            wordList.add(it)
        }
        // 读取test/resource的 words-gaokao.txt 文件，每行作为一个元素 得到一个列表
        service.newWordBookByWords(
            wordBook = WordBookResource.NewWordBookReq("高考单词"),
            wordList
        )
    }
}