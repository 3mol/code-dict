package cc.miaooo.infra.repository

import io.quarkus.hibernate.orm.panache.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.*

@Entity
@Table(name = "word_comment")
data class WordCommentPo(
    @Id
    @GeneratedValue
    val id: Long = 0,
    val content: String = "",
    @Column(name = "user_id")
    val userId: Long = 0,
    @Column(name = "word_id")
    val wordId: Long = 0
)

@ApplicationScoped
class WordCommentRepository : PanacheRepository<WordCommentPo> {

}
