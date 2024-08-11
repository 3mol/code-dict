package cc.miaooo.infra.repository

import cc.miaooo.domain.WordBook
import cc.miaooo.domain.WordBookItem
import io.quarkus.hibernate.orm.panache.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class WordBookRepository : PanacheRepository<WordBook> {
}
@ApplicationScoped
class WordBookItemRepository : PanacheRepository<WordBookItem> {
}
