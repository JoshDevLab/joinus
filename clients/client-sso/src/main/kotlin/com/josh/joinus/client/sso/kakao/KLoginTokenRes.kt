package com.josh.joinus.client.sso.kakao

data class KLoginTokenRes(
    private val accessToken: String,
    private val expiresIn: String,
    private val refreshToken: String,
    private val scope: String,
    private val tokenType: String,
    private val idToken: String,
    private val refreshTokenExpiresIn: String,
) {
    fun getAccess_token(): String {
        return "Bearer $accessToken"
    }
}