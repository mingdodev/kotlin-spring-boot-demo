package com.mykotlin.demo.global.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val title: String,
    val message: String,
    val httpStatus: HttpStatus,
    val code: String,
) {
}