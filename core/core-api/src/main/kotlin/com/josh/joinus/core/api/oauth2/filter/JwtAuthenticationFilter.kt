package com.josh.joinus.core.api.oauth2.filter

import com.josh.joinus.core.api.oauth2.token.JwtProvider
import com.josh.joinus.core.api.oauth2.token.JwtValidator
import com.josh.joinus.core.api.oauth2.token.UserAuthentication
import com.josh.joinus.core.api.oauth2.token.UserAuthentication.Companion.createDefaultUserAuthentication
import com.josh.joinus.core.api.oauth2.utils.HeaderUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter


class JwtAuthenticationFilter(
    private val jwtValidator: JwtValidator,
    private val jwtProvider: JwtProvider,
) :OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val accessToken = HeaderUtil.getAccessToken(request)
        jwtValidator.validateAccessToken(accessToken)
        setAuthentication(request, jwtProvider.getSubject(accessToken))
        filterChain.doFilter(request, response)
    }

    private fun setAuthentication(request: HttpServletRequest, userId: Long) {
        val authentication: UserAuthentication = createDefaultUserAuthentication(userId)
        createWebAuthenticationDetailsAndSet(request, authentication)
        val securityContext: SecurityContext = SecurityContextHolder.getContext()
        securityContext.authentication = authentication
    }

    private fun createWebAuthenticationDetailsAndSet(request: HttpServletRequest, authentication: UserAuthentication) {
        val webAuthenticationDetailsSource = WebAuthenticationDetailsSource()
        val webAuthenticationDetails = webAuthenticationDetailsSource.buildDetails(request)
        authentication.details = webAuthenticationDetails
    }

}