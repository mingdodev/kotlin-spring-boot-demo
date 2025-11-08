package com.mykotlin.demo.domain.post.api.response

import kotlinx.datetime.LocalDateTime

data class PostCreateResponse(
    val title: String,
    val content: String,
    val createdAt: LocalDateTime
)
