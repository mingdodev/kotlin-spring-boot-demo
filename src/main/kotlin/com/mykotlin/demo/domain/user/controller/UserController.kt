package com.mykotlin.demo.domain.user.controller

import com.mykotlin.demo.domain.user.api.request.UserCreateRequest
import com.mykotlin.demo.domain.user.api.response.UserCreateResponse
import com.mykotlin.demo.domain.user.api.toResponse
import com.mykotlin.demo.domain.user.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) {

    @PostMapping("/signup")
    fun signUp(@RequestBody request: UserCreateRequest): UserCreateResponse {
        val user = userService.signup(request)

        return user.toResponse()
    }
}