package com.josh.joinus.core.api.support.error

import com.fasterxml.jackson.databind.ObjectMapper
import com.josh.joinus.core.api.support.response.ApiResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.IOException


@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {
    private val objectMapper = ObjectMapper()

    @Throws(IOException::class)
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        authException: AuthenticationException?,
    ) {
        handleException(response)
    }

    @Throws(IOException::class)
    private fun handleException(response: HttpServletResponse) {
        setResponse(response, HttpStatus.UNAUTHORIZED, ErrorType.UNAUTHORIZED)
    }

    @Throws(IOException::class)
    private fun setResponse(response: HttpServletResponse, httpStatus: HttpStatus, errorType: ErrorType) {
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = "utf-8"
        response.status = httpStatus.value()
        val writer = response.writer
        writer.write(objectMapper.writeValueAsString(ApiResponse.unauthorized(errorType)))
    }
}