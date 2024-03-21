package com.josh.joinus.core.api.oauth2.utils

import com.josh.joinus.client.sso.kakao.KakaoInfo
import org.springframework.http.HttpHeaders
import java.net.URI
import java.net.URISyntaxException


class UrlUtil(
    val kakaoInfo: KakaoInfo
) {
    fun kakaoLogin(): HttpHeaders? {
        return createHttpHeader(kakaoInfo.kakaoUrlInit())
    }

    private fun createHttpHeader(str: String): HttpHeaders? {
        try {
            val uri = URI(str)
            val httpHeaders = HttpHeaders()
            httpHeaders.location = uri
            return httpHeaders
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
        return null
    }
}