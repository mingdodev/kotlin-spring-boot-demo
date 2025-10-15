package com.mykotlin.demo.domain.user.service

import com.mykotlin.demo.domain.user.api.request.UserCreateRequest
import com.mykotlin.demo.domain.user.model.User
import com.mykotlin.demo.domain.user.repository.UserDao
import com.mykotlin.demo.global.exception.BusinessException
import com.mykotlin.demo.global.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class UserService(val userDao: UserDao) {
    fun signup(request: UserCreateRequest): User {
        checkEmail(request.email)

        // TO DO: 비밀번호 암호화
        val passwordHash = "hashed_" + request.password

        val user = userDao.insert(
            name = request.name,
            email = request.email,
            passwordHash = passwordHash
        )

        return user
    }

    private fun checkEmail(email: String) {
        if (userDao.existsByEmail(email)) {
            throw BusinessException(ErrorCode.DUPLICATE_EMAIL)
        }
    }
}