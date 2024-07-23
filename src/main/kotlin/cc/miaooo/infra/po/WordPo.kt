package cc.miaooo.infra.po

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity()
@Table(name = "stardict")
data class WordPo(
    @Id
    @GeneratedValue
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
