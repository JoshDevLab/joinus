package com.josh.joinus.core.api.oauth2.utils

import com.josh.joinus.core.api.support.error.ErrorType
import com.josh.joinus.core.api.support.error.UnauthorizedException
import jakarta.servlet.http.HttpServletRequest

object HeaderUtil {
    private const val HEADER_AUTHORIZATION = "Authorization"
    private const val TOKEN_PREFIX = "Bearer "
    fun getAccessToken(request: HttpServletRequest): String {
        val headerValue = request.getHeader(HEADER_AUTHORIZATION)
        if (headerValue.isNullOrEmpty()) {
            throw UnauthorizedException(ErrorType.INVALID_ACCESS_TOKEN_VALUE)
        }

        if (headerValue.startsWith(TOKEN_PREFIX)) {
            return headerValue.substring(TOKEN_PREFIX.length)
        } else {
            throw UnauthorizedException(ErrorType.INVALID_ACCESS_TOKEN_VALUE)
        }
    }
}