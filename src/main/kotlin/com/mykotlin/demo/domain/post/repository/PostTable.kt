package com.mykotlin.demo.domain.post.repository

import com.mykotlin.demo.domain.user.repository.UserTable
import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.dao.id.LongIdTable
import org.jetbrains.exposed.v1.datetime.CurrentDateTime
import org.jetbrains.exposed.v1.datetime.datetime

object PostTable : LongIdTable("Posts") {
    val title = varchar("title", 20)
    val content = varchar("content", 255)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    val userId = reference(
        name ="user_id",
        refColumn = UserTable.id,
        fkName = "FK_POSTS__USERS",
        onDelete = ReferenceOption.CASCADE)
}