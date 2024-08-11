package cc.miaooo.domain

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "word_book")
data class WordBook(
    @Id
    @GeneratedValue
    val id: Long = 0,
    val name: String = "",
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.MIN,
    @Column(name = "updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    val updatedAt: LocalDateTime = LocalDateTime.MIN,
    // exclude field
    @Transient
    val items: List<Long> = listOf()
)

@Entity
@Table(name = "word_book_item")
data class WordBookItem(
    @Id
    @GeneratedValue
    val id: Long = 0,
    @Column(name = "word_book_id")
    val wordBookId: Long = 0,
    @Column(name = "word_id")
    val wordId: Long = 0,
    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.MIN,
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.MIN,
)

