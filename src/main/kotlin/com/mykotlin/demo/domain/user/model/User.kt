package com.mykotlin.demo.domain.user.model

import kotlinx.datetime.LocalDateTime

data class User(
    val id: Long,
    val name: String,
    val email: String,
    val password: String,
    val createdAt: LocalDateTime
)
