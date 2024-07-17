package cc.miaooo.infra.po

import io.quarkus.security.jpa.*
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity(name = "account")
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
