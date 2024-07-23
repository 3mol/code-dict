package cc.miaooo.application.vo

data class WordDetailVo(
    val id: Long = 0L,
    val word: String = "",
    val sw: String = "",
    val phonetic: String? = null,
    val definition: String? = null,
    val translation: String? = null,
    val pos: String? = null,
    val collins: Int? = null,
    val oxford: Int? = null,
    val tag: String? = null,
    val bnc: Int? = null,
    val frq: Int? = null,
    val exchange: String? = null,
    val detail: String? = null,
    val audio: String? = null,
)
