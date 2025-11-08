package com.mykotlin.demo.domain.post.service

import com.mykotlin.demo.domain.user.service.UserService
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class PostServiceTest(
    private val postService: PostService,
    private val userService: UserService,
) : DescribeSpec({

    describe("게시글 생성") {

        context("유효한 제목과 내용을 입력했을 경우") {
            it("게시글 생성에 성공한다") {
                val user = userService.signup("이름", "email@example.com", "password123")
                val post = postService.create("제목", "내용입니다.", user.id)

                post.id shouldNotBe null
                post.title shouldBe "제목"
                post.content shouldBe "내용입니다."
            }
        }
    }
}){
}