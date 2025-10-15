package com.mykotlin.demo.domain.user.api

import com.mykotlin.demo.domain.user.api.response.UserCreateResponse
import com.mykotlin.demo.domain.user.model.User

fun User.toResponse(): UserCreateResponse {
    return UserCreateResponse(
        userId = this.id,
        name = this.name,
        email = this.email,
    )
}