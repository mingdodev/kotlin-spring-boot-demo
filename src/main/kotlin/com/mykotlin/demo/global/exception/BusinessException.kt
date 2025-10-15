package com.mykotlin.demo.global.exception

class BusinessException(
    val errorCode: ErrorCode,
) : RuntimeException() {
}