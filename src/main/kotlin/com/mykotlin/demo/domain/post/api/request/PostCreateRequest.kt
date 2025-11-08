package com.mykotlin.demo.domain.post.api.request

import jakarta.validation.constraints.Size

data class PostCreateRequest(
    @field:Size(min = 1, max = 20, message = "제목은 최대 20자까지 입력 가능합니다.") val title: String,
    @field:Size(min = 1, max = 256, message = "내용은 최대 255자까지 입력 가능합니다.") val content: String,
    val userId: Long
)
