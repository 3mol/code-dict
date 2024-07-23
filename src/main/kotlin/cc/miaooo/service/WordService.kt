package cc.miaooo.service

import cc.miaooo.application.vo.WordDetailVo
import cc.miaooo.application.vo.WordSearchVo

interface WordService {

    fun detail(id: Long): WordDetailVo

    fun search(keywords: String): List<WordSearchVo>
}
