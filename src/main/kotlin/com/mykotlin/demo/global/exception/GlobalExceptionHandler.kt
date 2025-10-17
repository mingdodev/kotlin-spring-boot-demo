package com.mykotlin.demo.global.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.slf4j.LoggerFactory
import org.springframework.web.bind.MethodArgumentNotValidException

@RestControllerAdvice
class GlobalExceptionHandler {
    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(e: BusinessException): ResponseEntity<ProblemDetail> {
        val errorCode = e.errorCode

        log.warn("Business Exception [${errorCode.code}] Occurred: ${e.message}", e)

        val detail = ProblemDetail.forStatusAndDetail(errorCode.httpStatus, errorCode.message)
        detail.title = errorCode.title
        detail.setProperty("bizCode", errorCode.code)

        return ResponseEntity.of(detail).build()
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ProblemDetail> {
        log.warn("MethodArgumentNotValidException Occurred: ${e.bindingResult.fieldError?.defaultMessage}", e)

        val detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.bindingResult.fieldError?.defaultMessage)
        detail.title = "Invalid Parameters"

        return ResponseEntity.of(detail).build()
    }

    @ExceptionHandler(RuntimeException::class, Exception::class)
    fun handleAllUncaughtExceptions(e: Exception): ResponseEntity<ProblemDetail> {
        log.error("Uncaught Internal Server Error:", e)

        val detail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "예기치 못한 에러가 발생하였습니다.")
        detail.title = "Internal Server Error"

        return ResponseEntity.of(detail).build()
    }
}