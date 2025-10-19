package com.mykotlin.demo.domain.user.service

import com.mykotlin.demo.domain.user.api.request.UserCreateRequest
import com.mykotlin.demo.domain.user.model.User
import com.mykotlin.demo.domain.user.repository.UserRepository
import com.mykotlin.demo.global.exception.BusinessException
import com.mykotlin.demo.global.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun signup(request: UserCreateRequest): User {
        checkEmail(request.email)

        val passwordHash = passwordEncoder.encode(request.password)

        val userId = userRepository.insert(
            name = request.name,
            email = request.email,
            passwordHash = passwordHash
        )

        val user = userRepository.findById(userId)
            ?: throw BusinessException(ErrorCode.USER_NOT_FOUND)

        return user
    }

    private fun checkEmail(email: String) {
        if (userRepository.existsByEmail(email)) {
            throw BusinessException(ErrorCode.DUPLICATE_EMAIL)
        }
    }
}