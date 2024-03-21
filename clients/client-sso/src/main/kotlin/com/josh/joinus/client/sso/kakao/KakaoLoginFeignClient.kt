package com.josh.joinus.client.sso.kakao

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody


@FeignClient(
    name = "kakaoLoginFeignClient",
    url = "\${oauth2.kakao.baseUrl}",
    configuration = [KakaoFeignConfiguration::class],
)
@Component
interface KakaoLoginFeignClient {
    @PostMapping(value = ["/oauth/token"])
    fun getToken(
        @RequestBody authorization: String,
    ): KLoginTokenRes
}