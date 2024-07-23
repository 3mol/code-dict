package cc.miaooo.service

import cc.miaooo.application.vo.WordDetailVo
import cc.miaooo.application.vo.WordSearchVo
import cc.miaooo.infra.repository.WordRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class WordServiceImpl(val wordRepository: WordRepository) : WordService {

    override fun detail(id: Long): WordDetailVo {
        val word = wordRepository.detail(id);
        return WordDetailVo(word.id, word.word, word.sw, word.phonetic, word.definition, word.translation)
    }

    override fun search(keyword: String): List<WordSearchVo> {
        TODO("Not yet implemented")
    }
}