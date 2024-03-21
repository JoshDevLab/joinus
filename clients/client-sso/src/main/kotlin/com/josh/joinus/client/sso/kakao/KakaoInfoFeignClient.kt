package com.josh.joinus.client.sso.kakao

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.cloud.openfeign.FeignClientProperties.FeignClientConfiguration
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader


@FeignClient(
    name = "kakaoInfoFeignClient",
    url = "\${oauth2.kakao.infoUrl}",
    configuration = [FeignClientConfiguration::class],
)
@Component
interface KakaoInfoFeignClient {
    @GetMapping("/v1/user/access_token_info")
    fun getInfo(@RequestHeader(name = "Authorization") accessToken: String): KTokenInfoRes
}