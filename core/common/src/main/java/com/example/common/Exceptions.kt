package com.example.common

open class AppException(
    message: String = "",
    cause: Throwable? = null,
) : Exception(message, cause)

class ConnectionException(cause: Exception) : AppException(cause = cause)

open class RemoteServiceException(
    message: String,
    cause: Exception? = null
) : AppException(message, cause)

class AuthException(cause: Exception? = null) : AppException(cause = cause)

class StorageException(cause: Exception) : AppException(cause = cause)

class UserFriendlyException(
    val userFriendlyMessage: String,
    cause: Exception,
) : AppException(cause.message ?: "", cause)

class NotFoundException(
    message: String
) : AppException(message)
