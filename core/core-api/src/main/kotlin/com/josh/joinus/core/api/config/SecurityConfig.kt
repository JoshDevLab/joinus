package com.josh.joinus.core.api.config

import com.josh.joinus.core.api.oauth2.filter.ExceptionHandlerFilter
import com.josh.joinus.core.api.oauth2.filter.JwtAuthenticationFilter
import com.josh.joinus.core.api.oauth2.token.JwtProvider
import com.josh.joinus.core.api.oauth2.token.JwtValidator
import com.josh.joinus.core.api.support.error.JwtAuthenticationEntryPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@EnableWebSecurity
@Configuration
class SecurityConfig(
        private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
        private val jwtProvider: JwtProvider,
        private val jwtValidator: JwtValidator,
) {

    companion object {
        private val whiteList = arrayOf("/api/user/signin", "/api/user/signup", "/api/user/reissue", "/")
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web -> web.ignoring().requestMatchers(*whiteList) }
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .exceptionHandling {
                it.authenticationEntryPoint(jwtAuthenticationEntryPoint)
            }
            .authorizeHttpRequests {
                it.anyRequest().authenticated()
            }
            .addFilterBefore(
                    JwtAuthenticationFilter(jwtValidator, jwtProvider), UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(ExceptionHandlerFilter(), JwtAuthenticationFilter::class.java)
            .build()


}
