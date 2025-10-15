package com.mykotlin.demo.domain.user.api.request

data class UserCreateRequest(
    val name: String,
    val email: String,
    val password: String
)
