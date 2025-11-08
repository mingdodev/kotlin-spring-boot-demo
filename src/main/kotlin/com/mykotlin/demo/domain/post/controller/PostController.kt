package com.mykotlin.demo.domain.post.controller

import com.mykotlin.demo.domain.post.api.request.PostCreateRequest
import com.mykotlin.demo.domain.post.api.response.PostCreateResponse
import com.mykotlin.demo.domain.post.api.toCreateResponse
import com.mykotlin.demo.domain.post.model.Post
import com.mykotlin.demo.domain.post.service.PostService
import com.mykotlin.demo.global.response.ApiResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/posts")
class PostController(private val postService: PostService) {

    @PostMapping("")
    fun create (@RequestBody request: PostCreateRequest): ApiResponse<PostCreateResponse> {
        val post = postService.create(request.title, request.content, request.userId);

        return ApiResponse.success(post.toCreateResponse(), "게시글 작성에 성공하였습니다.")
    }
}