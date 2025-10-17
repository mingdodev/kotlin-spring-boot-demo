package com.mykotlin.demo.global.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val title: String,
    val message: String,
    val httpStatus: HttpStatus,
    val code: String,
) {
    // User
    USER_NOT_FOUND("Data Not Found", "사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND, "user-not-found"),
    DUPLICATE_EMAIL("Data Conflict", "이미 존재하는 이메일입니다.", HttpStatus.CONFLICT, "duplicate-email"),
}