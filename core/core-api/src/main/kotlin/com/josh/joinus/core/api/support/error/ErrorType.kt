package com.josh.joinus.core.api.support.error

import org.springframework.boot.logging.LogLevel
import org.springframework.http.HttpStatus

enum class ErrorType(val status: HttpStatus, val code: ErrorCode, val message: String, val logLevel: LogLevel) {
    DEFAULT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.E500, "An unexpected error has occurred.", LogLevel.ERROR),
    EXPIRED_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, ErrorCode.E401, "Expired JWT token.", LogLevel.INFO),
    INVALID_ACCESS_TOKEN_VALUE(HttpStatus.UNAUTHORIZED, ErrorCode.E401, "Invalid access token", LogLevel.INFO),
    EXPIRED_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, ErrorCode.E401, "Expired JWT refresh token.", LogLevel.INFO),
    INVALID_REFRESH_TOKEN_VALUE(HttpStatus.UNAUTHORIZED, ErrorCode.E401, "Invalid refresh token", LogLevel.INFO),
    NOT_MATCH_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, ErrorCode.E401, "Not match refresh token.", LogLevel.INFO)
}
