package com.josh.joinus.core.api.oauth2.token

import ch.qos.logback.core.status.ErrorStatus
import com.josh.joinus.core.api.support.error.ErrorType
import com.josh.joinus.core.api.support.error.UnauthorizedException
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.stereotype.Component


@Component
class JwtValidator(
    private val jwtGenerator: JwtGenerator,
) {

    fun validateAccessToken(accessToken: String?) {
        try {
            val jwtParser = jwtGenerator.getJwtParser()
            jwtParser.parseClaimsJws(accessToken)
        } catch (e: ExpiredJwtException) {
            throw UnauthorizedException(ErrorType.EXPIRED_ACCESS_TOKEN)
        } catch (e: Exception) {
            throw UnauthorizedException(ErrorType.INVALID_ACCESS_TOKEN_VALUE)
        }
    }

    fun validateRefreshToken(refreshToken: String?) {
        try {
            val jwtParser = jwtGenerator.getJwtParser()
            jwtParser.parseClaimsJws(refreshToken)
        } catch (e: ExpiredJwtException) {
            throw UnauthorizedException(ErrorType.EXPIRED_REFRESH_TOKEN)
        } catch (e: java.lang.Exception) {
            throw UnauthorizedException(ErrorType.INVALID_REFRESH_TOKEN_VALUE)
        }
    }

    fun equalsRefreshToken(refreshToken: String, storedRefreshToken: String) {
        if (refreshToken != storedRefreshToken) {
            throw UnauthorizedException(ErrorType.NOT_MATCH_REFRESH_TOKEN)
        }
    }
}