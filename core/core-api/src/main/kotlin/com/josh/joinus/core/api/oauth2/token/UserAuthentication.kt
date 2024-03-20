package com.josh.joinus.core.api.oauth2.token

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority


class UserAuthentication private constructor(
    principal: Any,
    credentials: Any?,
    authorities: Collection<GrantedAuthority?>?,
) :
    UsernamePasswordAuthenticationToken(principal, credentials, authorities) {
    companion object {
        fun createDefaultUserAuthentication(userId: Long): UserAuthentication {
            return UserAuthentication(userId, null, null)
        }
    }
}