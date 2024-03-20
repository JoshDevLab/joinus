package com.josh.joinus.core.api.oauth2.token

import io.jsonwebtoken.Header
import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component
import java.security.Key
import java.util.Base64
import java.util.Date

@Component
@PropertySource("classpath:secure.properties")
class JwtGenerator(
    @Value("\${jwt.secret}")
    private val secretKey: String,

    @Value("\${jwt.access-token-expire-time}")
    private val ACCESS_TOKEN_EXPIRE_TIME: Long,

    @Value("\${jwt.refresh-token-expire-time}")
    private val REFRESH_TOKEN_EXPIRE_TIME: Long,
) {

    fun generateToken(userId: Long, isAccessToken: Boolean): String {
        val now: Date = generateNowDate()
        val expiration: Date = generateExpirationDate(isAccessToken, now)
        return Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setSubject(userId.toString())
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    fun getJwtParser(): JwtParser {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
    }

    private fun generateNowDate(): Date {
        return Date()
    }

    private fun generateExpirationDate(isAccessToken: Boolean, now: Date): Date {
        return Date(now.getTime() + calculateExpireTime(isAccessToken))
    }

    private fun calculateExpireTime(isAccessToken: Boolean): Long {
        if (isAccessToken) {
            return ACCESS_TOKEN_EXPIRE_TIME
        }
        return REFRESH_TOKEN_EXPIRE_TIME
    }

    private fun getSigningKey(): Key {
        val encoded = Base64.getEncoder().encodeToString(secretKey.toByteArray())
        return Keys.hmacShaKeyFor(encoded.toByteArray())
    }

}
