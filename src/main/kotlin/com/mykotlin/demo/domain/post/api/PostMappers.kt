package com.mykotlin.demo.domain.post.api

import com.mykotlin.demo.domain.post.api.response.PostCreateResponse
import com.mykotlin.demo.domain.post.model.Post

fun Post.toCreateResponse(): PostCreateResponse {
    return PostCreateResponse(
        title = this.title,
        content = this.content,
        createdAt = this.createdAt
    )
}