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
        id = this[UserTable.id].value,
        name = this[UserTable.name],
        email = this[UserTable.email],
        password = this[UserTable.password],
        createdAt = this[UserTable.createdAt]
    )

    override fun insert(name: String, email: String, passwordHash: String): Long = transaction {
        UserTable.insertAndGetId {
            it[UserTable.name] = name
            it[UserTable.email] = email
            it[UserTable.password] = passwordHash
        }.value
    }

    override fun findById(id: Long): User? = transaction {
        UserTable.selectAll().where { UserTable.id eq id }
            .singleOrNull()
            ?.toDomain()
    }

    override fun existsByEmail(email: String): Boolean = transaction {
        UserTable.select(UserTable.id)
            .where { UserTable.email eq email }
            .limit(1)
            .count() > 0
    }
}