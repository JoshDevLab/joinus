package com.josh.joinus.core.api.oauth2.token

import org.springframework.stereotype.Component

@Component
class JwtProvider(
    private val jwtGenerator: JwtGenerator,
) {
    fun issueToken(userId: Long) :Token {
        return Token(
            jwtGenerator.generateToken(userId, isAccessToken = true),
            jwtGenerator.generateToken(userId, isAccessToken = false),
        )
    }

    fun getSubject(token: String): Long {
        val jwtParser = jwtGenerator.getJwtParser()
        return jwtParser.parseClaimsJws(token)
            .body
            .subject.toLong()
    }
}