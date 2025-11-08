package com.mykotlin.demo.domain.post.repository

import com.mykotlin.demo.domain.post.model.Post

interface PostRepository {
    fun create(title: String, content: String, userId: Long): Post
    fun findById(id: Long): Post?
}