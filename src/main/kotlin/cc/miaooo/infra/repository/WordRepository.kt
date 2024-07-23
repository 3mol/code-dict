package cc.miaooo.infra.repository

import cc.miaooo.infra.po.WordPo
import io.quarkus.hibernate.orm.panache.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class WordRepository : PanacheRepository<WordPo> {
    fun detail(id: Long): WordPo {
        return findById(id);
    }

    fun search(keywords: String): List<WordPo> {
        return list("word like ?1 or translation like ?1 or sw like ?1 order by frq limit 20", "%$keywords%")
    }
}