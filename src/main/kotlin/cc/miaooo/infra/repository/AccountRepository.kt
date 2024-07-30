package cc.miaooo.infra.repository

import io.quarkus.hibernate.orm.panache.PanacheRepository
import io.quarkus.security.jpa.Password
import io.quarkus.security.jpa.Roles
import io.quarkus.security.jpa.UserDefinition
import io.quarkus.security.jpa.Username
import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "account")
@UserDefinition
data class AccountPo(
    @Id
    @GeneratedValue
    val id: Long? = null,
    @Username val name: String = "",
    @Password val pwd: String = "",
    @Roles val roles: String = "",
    val phone: String = "",
    val email: String = "",
)

@ApplicationScoped
class AccountRepository : PanacheRepository<AccountPo> {
    fun detail(id: Long): AccountPo {
        return findById(id)
    }
}
