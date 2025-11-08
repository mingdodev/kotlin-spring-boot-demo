package com.mykotlin.demo.domain.user.controller

import com.mykotlin.demo.domain.user.api.request.UserCreateRequest
import com.mykotlin.demo.domain.user.api.response.UserCreateResponse
import com.mykotlin.demo.domain.user.api.toCreateResponse
import com.mykotlin.demo.domain.user.service.UserService
import com.mykotlin.demo.global.response.ApiResponse
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) {

    @PostMapping("/signup")
    fun signUp(@RequestBody @Valid request: UserCreateRequest): ApiResponse<UserCreateResponse> {
        val user = userService.signup(request.name, request.email, request.password)

        return ApiResponse.success(user.toCreateResponse(), "회원 가입에 성공했습니다.")
    }
}