package com.mykotlin.demo.domain.post.model

import kotlinx.datetime.LocalDateTime

data class Post(
    val id: Long,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val userId: Long
)
