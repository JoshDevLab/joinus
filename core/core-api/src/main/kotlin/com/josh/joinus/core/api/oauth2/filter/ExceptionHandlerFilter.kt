package com.josh.joinus.core.api.oauth2.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.josh.joinus.core.api.support.error.ErrorType
import com.josh.joinus.core.api.support.error.UnauthorizedException
import com.josh.joinus.core.api.support.response.ApiResponse
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException


class ExceptionHandlerFilter :OncePerRequestFilter() {
    private val objectMapper = ObjectMapper()
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: UnauthorizedException) {
            handleUnauthorizedException(response, e)
        } catch (ee: Exception) {
            handleException(response)
        }
    }

    @Throws(IOException::class)
    private fun handleUnauthorizedException(response: HttpServletResponse, e: java.lang.Exception) {
        val ue = e as UnauthorizedException
        val errorType: ErrorType = ue.errorType
        val httpStatus: HttpStatus = errorType.status
        setResponse(response, httpStatus, errorType)
    }

    @Throws(IOException::class)
    private fun handleException(response: HttpServletResponse) {
        setResponse(response, HttpStatus.INTERNAL_SERVER_ERROR, ErrorType.DEFAULT_ERROR)
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