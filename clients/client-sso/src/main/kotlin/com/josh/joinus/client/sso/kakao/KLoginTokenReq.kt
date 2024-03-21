package com.josh.joinus.client.sso.kakao

data class KLoginTokenReq(
    private val code: String,
    private val clientId: String,
    private val clientSecret: String,
    private val redirectUri: String,
    private val grantType: String = "authorization_code"
) {

    override fun toString(): String {
        return "code=" + code + '&' +
                "client_id=" + clientId + '&' +
                "client_secret=" + clientSecret + '&' +
                "redirect_uri=" + redirectUri + '&' +
                "grant_type=" + grantType
    }

}