package cc.miaooo.service

import cc.miaooo.application.vo.WordDetailVo
import cc.miaooo.application.vo.WordSearchVo

interface WordService {

    fun detail(id: Long): WordDetailVo

    fun detail(word: String): WordDetailVo

    fun search(keywords: String): List<WordSearchVo>

    fun random(size: Int, tag: String = "cet4"): List<WordSearchVo>
    fun batch(ids: List<Long>): List<WordDetailVo>
}
