package com.mykotlin.demo

import com.mykotlin.demo.domain.user.service.PasswordEncoder
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class TestConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return TestPasswordEncoder()
    }
}

private class TestPasswordEncoder : PasswordEncoder {
    private val testHashPrefix = "test_hash_"
    override fun encode(rawPassword: String): String {
        return testHashPrefix + rawPassword
    }
    override fun matches(rawPassword: String, encodedPassword: String): Boolean {
        return encodedPassword == (testHashPrefix + rawPassword)
    }
}