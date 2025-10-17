package com.mykotlin.demo.domain.user.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.mykotlin.demo.domain.user.api.request.UserCreateRequest
import com.mykotlin.demo.global.response.ApiResponse
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.contain
import org.junit.jupiter.api.Assertions.*
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.assertj.MockMvcTester
import org.springframework.test.web.servlet.assertj.MvcTestResult
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserApiTest(
    private val userController: UserController,
    private val mockMvcTester: MockMvcTester,
    private val objectMapper: ObjectMapper,
) : DescribeSpec({

    describe("회원 가입 요청") {

        context("유효한 요청인 경우") {
            it("200을 반환한다") {
                val request = UserCreateRequest("NewName", "new@example.com", "password")
                val requestJson = objectMapper.writeValueAsString(request)

                val result = mockMvcTester.post().uri("/signup")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestJson).exchange()

                result.response.status shouldBe 200

                val apiResponseJson = objectMapper.readTree(result.response.contentAsString)
                apiResponseJson.get("data").get("name").asText() shouldBe "NewName"
                apiResponseJson.get("data").get("email").asText() shouldBe "new@example.com"
            }
        }

        context("올바르지 않은 이메일 형식을 입력했을 경우") {
            it("400을 반환한다") {
                val request = UserCreateRequest("NewName", "new", "password")
                val requestJson = objectMapper.writeValueAsString(request)

                val result = mockMvcTester.post().uri("/signup")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(requestJson).exchange()

                result.response.status shouldBe 400

                val jsonString = result.response.contentAsString
                val apiResponseJson = objectMapper.readTree(jsonString)

                println(apiResponseJson)

                apiResponseJson.get("title").asText() shouldBe "Invalid Parameters"
                apiResponseJson.get("detail").asText() should contain("올바른 이메일 형식이 아닙니다.")
            }
        }

        context("이름 글자수를 초과하여 입력했을 경우") {
            it("400을 반환한다") {
                val request = UserCreateRequest("NewNameNewNameNewNameNewName", "new@example.com", "password")
                val requestJson = objectMapper.writeValueAsString(request)

                val result = mockMvcTester.post().uri("/signup")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestJson).exchange()

                result.response.status shouldBe 400

                val jsonString = result.response.contentAsString
                val apiResponseJson = objectMapper.readTree(jsonString)

                println(apiResponseJson)

                apiResponseJson.get("title").asText() shouldBe "Invalid Parameters"
                apiResponseJson.get("detail").asText() should contain("이름은 최소 2글자, 최대 10글자로 입력해주세요.")
            }
        }
    }
}){

}