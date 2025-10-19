package com.mykotlin.demo.domain.user.repository

import com.mykotlin.demo.domain.user.model.User

interface UserRepository {
    fun insert(name: String, email: String, passwordHash: String): Long
    fun findById(id: Long): User?
    fun existsByEmail(email: String): Boolean
}