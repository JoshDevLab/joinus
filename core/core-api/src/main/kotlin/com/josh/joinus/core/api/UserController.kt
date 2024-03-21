package com.josh.joinus.core.api

import com.josh.joinus.core.api.oauth2.KakaoOAuthProvider
import com.josh.joinus.core.api.support.response.ApiResponse
import com.josh.joinus.core.domain.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/user")
class UserController(
    val userService: UserService,
    val kakaoOAuthProvider: KakaoOAuthProvider,
) {

    @GetMapping("/login/kakao")
    fun kakaoLogin(): ResponseEntity<Any> {
        val httpHeaders = kakaoOAuthProvider.kakaoLogin()
        return ResponseEntity(
            httpHeaders,
            HttpStatus.SEE_OTHER,
        )
    }

}