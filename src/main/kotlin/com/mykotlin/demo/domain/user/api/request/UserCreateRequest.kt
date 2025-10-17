package com.mykotlin.demo.domain.user.api.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size

data class UserCreateRequest(
    @field:Size(min = 2, max = 10, message = "이름은 최소 2글자, 최대 10글자로 입력해주세요.") val name: String,
    @field:Email("올바른 이메일 형식이 아닙니다.") val email: String,
    @field:Size(min = 8, max = 20) val password: String
)
