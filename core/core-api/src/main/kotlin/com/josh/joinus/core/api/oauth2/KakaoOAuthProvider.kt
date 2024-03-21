package com.josh.joinus.core.api.oauth2

import com.josh.joinus.client.sso.kakao.KakaoInfo
import com.josh.joinus.core.api.support.error.ErrorType
import com.josh.joinus.core.api.support.error.UnauthorizedException
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import java.net.URI
import java.net.URISyntaxException

@Component
class KakaoOAuthProvider(
    val kakaoInfo: KakaoInfo
) {
    fun kakaoLogin(): HttpHeaders? {
        return createHttpHeader(kakaoInfo.kakaoUrlInit())
    }

    private fun createHttpHeader(str: String): HttpHeaders {
        try {
            val uri = URI(str)
            val httpHeaders = HttpHeaders()
            httpHeaders.location = uri
            return httpHeaders
        } catch (e: URISyntaxException) {
            e.printStackTrace()
            throw UnauthorizedException(ErrorType.BAD_REQUEST)
        }
    }
}