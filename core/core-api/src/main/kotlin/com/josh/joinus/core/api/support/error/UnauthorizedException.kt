package com.josh.joinus.core.api.support.error

class UnauthorizedException(
    val errorType: ErrorType,
    val data: Any? = null,
) : RuntimeException(errorType.message)
