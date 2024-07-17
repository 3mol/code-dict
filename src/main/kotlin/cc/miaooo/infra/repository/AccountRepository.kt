package cc.miaooo.infra.repository

import cc.miaooo.infra.po.AccountPo
import io.quarkus.hibernate.orm.panache.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class AccountRepository : PanacheRepository<AccountPo> {
    fun detail(id: Long): AccountPo {
        return findById(id)
    }
}
