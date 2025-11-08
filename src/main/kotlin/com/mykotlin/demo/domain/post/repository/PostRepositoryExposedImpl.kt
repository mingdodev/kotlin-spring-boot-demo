package com.mykotlin.demo.domain.post.repository

import com.mykotlin.demo.domain.post.model.Post
import com.mykotlin.demo.domain.user.model.User
import com.mykotlin.demo.domain.user.repository.UserTable
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.insertAndGetId
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class PostRepositoryExposedImpl : PostRepository {

    private fun ResultRow.toDomain(): Post = Post(
        id = this[PostTable.id].value,
        title = this[PostTable.title],
        content = this[PostTable.content],
        userId = this[PostTable.userId]!!.value,
        createdAt = this[PostTable.createdAt]
    )

    override fun create(
        title: String,
        content: String,
        userId: Long
    ): Post = transaction {
        val postId = PostTable.insertAndGetId {
            it[PostTable.title] = title
            it[PostTable.content] = content
            it[PostTable.userId] = userId
        }.value

        PostTable.selectAll()
            .where { PostTable.id eq postId }
            .first()
            .toDomain()
    }

    override fun findById(id: Long): Post? {
        TODO("Not yet implemented")
    }

}