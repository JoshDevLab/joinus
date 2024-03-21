package com.josh.joinus.client.sso.kakao

data class KTokenInfoRes(
    private val id: String,
    private val expiresIn: String,
    private val appId: String,
) {
}