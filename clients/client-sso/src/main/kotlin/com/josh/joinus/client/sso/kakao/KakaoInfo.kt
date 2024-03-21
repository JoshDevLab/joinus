package com.josh.joinus.client.sso.kakao

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component
import java.util.stream.Collectors


@Component
@PropertySource("classpath:sso.properties")
class KakaoInfo(
    @Value("oauth2.kakao.baseUrl")
    private val baseUrl: String,

    @Value("oauth2.kakao.infoUrl")
    private val infoUrl: String,

    @Value("oauth2.kakao.clientId")
    private val clientId: String,

    @Value("oauth2.kakao.secretKey")
    private val secretKey: String,

    @Value("oauth2.kakao.redirectUri")
    private val redirectUri: String,
) {
    fun kakaoUrlInit(): String {
        val params: MutableMap<String, String> = HashMap()
        params["client_id"] = clientId
        params["redirect_uri"] = redirectUri
        params["response_type"] = "code"

        val paramStr = params.entries.stream()
            .map { param -> param.key + "=" + param.value }
            .collect(Collectors.joining("&"))

        return (baseUrl
                + "/oauth/authorize"
                + "?"
                + paramStr)
    }

}