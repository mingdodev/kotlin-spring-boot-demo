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
    fun signup(name: String, email: String, password: String): User {
        checkEmail(email)

        val passwordHash = passwordEncoder.encode(password)

        val userId = userRepository.insert(
            name = name,
            email = email,
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