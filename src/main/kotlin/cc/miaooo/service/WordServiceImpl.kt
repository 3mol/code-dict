package cc.miaooo.service

import cc.miaooo.application.vo.WordDetailVo
import cc.miaooo.application.vo.WordSearchVo
import cc.miaooo.infra.repository.WordPo
import cc.miaooo.infra.repository.WordRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class WordServiceImpl(val wordRepository: WordRepository) : WordService {

    override fun detail(id: Long): WordDetailVo {
        val word = wordRepository.findById(id);
        return WordDetailVo(
            word.id,
            word.word,
            word.sw,
            word.phonetic,
            word.definition,
            word.translation,
            word.pos,
            word.collins,
            word.oxford,
            word.tag,
            word.bnc,
            word.frq,
            word.exchange,
            word.detail,
            word.audio
        )
    }

    override fun search(keywords: String): List<WordSearchVo> {
        val words = wordRepository.search(keywords);
        return words.map(poToSearchVo()).sortedBy { it.word.length }
    }

    private fun poToSearchVo(): (WordPo) -> WordSearchVo =
        {
            WordSearchVo(
                it.id,
                it.word,
                it.sw,
                it.phonetic,
                it.definition,
                it.translation,
                it.pos,
                it.collins,
                it.oxford,
                it.tag,
                it.bnc,
                it.frq,
                it.exchange,
                it.detail,
                it.audio
            )
        }

    override fun detail(word: String): WordDetailVo {
        // todo fix ci
        // SELECT * FROM stardict WHERE word COLLATE utf8mb4_bin = 'race';
        val wordPo = wordRepository.find("word", word).singleResultOptional<WordPo>()
        return wordPo.map(poToDetailVo()).orElse(WordDetailVo());
    }

    private fun poToDetailVo(): (t: WordPo) -> WordDetailVo =
        {
            WordDetailVo(
                it.id,
                it.word,
                it.sw,
                it.phonetic,
                it.definition,
                it.translation,
                it.pos,
                it.collins,
                it.oxford,
                it.tag,
                it.bnc,
                it.frq,
                it.exchange,
                it.detail,
                it.audio
            )
        }

    override fun random(size: Int, tag: String): List<WordSearchVo> {
        val random = wordRepository.random(size, "1")
        return random.map(poToSearchVo())
    }

    override fun batch(ids: List<Long>): List<WordDetailVo> {
        val words = wordRepository.list("id in ?1", ids)
        return words.map(poToDetailVo())
    }
}