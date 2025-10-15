package com.mykotlin.demo.domain.user.api.response

data class UserCreateResponse(
    val userId: Long,
    val name: String,
    val email: String,
)
