package com.mykotlin.demo.global.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val title: String,
    val message: String,
    val httpStatus: HttpStatus,
    val code: String,
) {
    // User
    USER_NOT_FOUND("Data Not Found Error", "사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND, "USER_NOT_FOUND"),
    DUPLICATE_EMAIL("Data Conflict Error", "이미 존재하는 이메일입니다.", HttpStatus.CONFLICT, "DUPLICATE_EMAIL"),
}