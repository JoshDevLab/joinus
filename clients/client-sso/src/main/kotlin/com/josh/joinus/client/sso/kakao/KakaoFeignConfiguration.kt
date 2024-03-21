package com.josh.joinus.client.sso.kakao

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.context.annotation.Bean


class KakaoFeignConfiguration {
    @Bean
    fun requestInterceptor(): RequestInterceptor {
        return RequestInterceptor { template: RequestTemplate ->
            template.header(
                "Content-Type",
                "application/x-www-form-urlencoded",
            )
        }
    }

}