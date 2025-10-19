package com.mykotlin.demo.domain.user.repository

import org.jetbrains.exposed.v1.core.dao.id.LongIdTable
import org.jetbrains.exposed.v1.datetime.CurrentDateTime
import org.jetbrains.exposed.v1.datetime.datetime

object UserTable : LongIdTable("Users") {
    val name = varchar("name", 20)
    val email = varchar("email", 50).uniqueIndex()
    val password = varchar("password", 255)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
}