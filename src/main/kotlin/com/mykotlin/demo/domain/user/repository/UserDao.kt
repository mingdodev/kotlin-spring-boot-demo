package com.mykotlin.demo.domain.user.repository

import com.mykotlin.demo.domain.user.model.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class UserDao {

    private fun ResultRow.toDomain(): User = User(
        id = this[Users.id].value,
        name = this[Users.name],
        email = this[Users.email],
        password = this[Users.password],
        createdAt = this[Users.createdAt]
    )

    fun insert(name: String, email: String, passwordHash: String): User = transaction {
        val newId = Users.insertAndGetId {
            it[Users.name] = name
            it[Users.email] = email
            it[Users.password] = passwordHash
        }

        Users.selectAll().where { Users.id eq newId }
            .single()
            .toDomain()
    }

    fun existsByEmail(email: String): Boolean = transaction {
        Users.select(Users.id)
            .where { Users.email eq email }
            .limit(1)
            .count() > 0
    }
}