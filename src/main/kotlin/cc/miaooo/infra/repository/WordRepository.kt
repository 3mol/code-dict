package cc.miaooo.infra.repository

import cc.miaooo.infra.po.WordPo
import io.quarkus.hibernate.orm.panache.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class WordRepository : PanacheRepository<WordPo> {
    fun detail(id: Long): WordPo {
        return findById(id);
    }
}