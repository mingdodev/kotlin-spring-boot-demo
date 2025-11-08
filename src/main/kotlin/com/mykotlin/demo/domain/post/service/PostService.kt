package com.mykotlin.demo.domain.post.service

import com.mykotlin.demo.domain.post.model.Post
import com.mykotlin.demo.domain.post.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository
) {
    fun create(title: String, content: String, userId: Long): Post {
        // get current user info

        val post = postRepository.create(
            title = title,
            content = content,
            userId = userId
        )

        return post
    }
}