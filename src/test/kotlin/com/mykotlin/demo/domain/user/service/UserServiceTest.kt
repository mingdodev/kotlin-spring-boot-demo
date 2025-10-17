package com.mykotlin.demo.domain.user.service

import com.mykotlin.demo.domain.user.api.request.UserCreateRequest
import com.mykotlin.demo.global.exception.BusinessException
import com.mykotlin.demo.global.exception.ErrorCode
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class UserServiceTest(
    private val userService: UserService,
) : DescribeSpec({

    describe("회원 가입") {

        context("유효한 정보를 입력했을 경우") {
            it("사용자가 성공적으로 생성되어야 한다") {
                val request = UserCreateRequest("NewName", "new@example.com", "password")

                val user = userService.signup(request)

                user.id shouldNotBe null
                user.name shouldBe "NewName"
                user.email shouldBe "new@example.com"
            }
        }

        context("이미 존재하는 이메일을 입력했을 경우") {
            it("BusinessException이 발생한다") {
                val request2 = UserCreateRequest("NewName2", "new@example.com", "password2")

                val exception = shouldThrow<BusinessException> {
                    userService.signup(request2)
                }

                exception.errorCode shouldBe ErrorCode.DUPLICATE_EMAIL
            }
        }
    }
}) {
}