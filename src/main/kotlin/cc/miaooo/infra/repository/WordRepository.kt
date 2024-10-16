package cc.miaooo.infra.repository

import io.quarkus.hibernate.orm.panache.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
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

@ApplicationScoped
class WordRepository : PanacheRepository<WordPo> {
    fun detail(id: Long): WordPo {
        return findById(id);
    }

    fun search(keywords: String): List<WordPo> {
        return list(
            "word like ?1 order by frq limit 20",
            "$keywords%"
        )
    }

    fun random(size: Int, tag: String): List<WordPo> {
        return list(
            "collins =?1 order by frq limit ?2",
            tag, size
        )
    }
}