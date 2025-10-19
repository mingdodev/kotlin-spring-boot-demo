package com.mykotlin.demo.domain.user.service

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class BCryptPasswordEncoder(
    private val bCryptEncoder: BCryptPasswordEncoder = BCryptPasswordEncoder()
) : PasswordEncoder{

    override fun encode(rawPassword: String): String {
        return bCryptEncoder.encode(rawPassword)
    }

    override fun matches(rawPassword: String, encodedPassword: String): Boolean {
        return bCryptEncoder.matches(rawPassword, encodedPassword)
    }
}