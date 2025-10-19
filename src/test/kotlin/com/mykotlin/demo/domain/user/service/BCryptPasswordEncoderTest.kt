package com.mykotlin.demo.domain.user.service

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class BCryptPasswordEncoderTest : DescribeSpec({
    val encoder = BCryptPasswordEncoder()
    val rawPassword = "password123"

    describe("Spring Security의 BCryptPasswordEncoder를 통한 비밀번호 암호화") {

        context("비밀번호 암호화를 여러 번 할 경우") {
            it("매번 다른 값을 생성한다") {
                val hashedPassword1 = encoder.encode(rawPassword)
                val hashedPassword2 = encoder.encode(rawPassword)

                hashedPassword1 shouldNotBe hashedPassword2
            }
        }

        context("평문과 해시 값이 일치하는 경우") {
            it("matches는 참을 반환한다") {
                val hashedPassword = encoder.encode(rawPassword)

                encoder.matches(rawPassword, hashedPassword) shouldBe true
            }
        }
    }
}) {
}