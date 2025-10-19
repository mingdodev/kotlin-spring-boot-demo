package com.mykotlin.demo.domain.user.repository

import com.mykotlin.demo.domain.user.model.User
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.insertAndGetId
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryExposedImpl : UserRepository {

    private fun ResultRow.toDomain(): User = User(
        id = this[Users.id].value,
        name = this[Users.name],
        email = this[Users.email],
        password = this[Users.password],
        createdAt = this[Users.createdAt]
    )

    override fun insert(name: String, email: String, passwordHash: String): Long = transaction {
        Users.insertAndGetId {
            it[Users.name] = name
            it[Users.email] = email
            it[Users.password] = passwordHash
        }.value
    }

    override fun findById(id: Long): User? = transaction {
        Users.selectAll().where { Users.id eq id }
            .singleOrNull()
            ?.toDomain()
    }

    override fun existsByEmail(email: String): Boolean = transaction {
        Users.select(Users.id)
            .where { Users.email eq email }
            .limit(1)
            .count() > 0
    }
}