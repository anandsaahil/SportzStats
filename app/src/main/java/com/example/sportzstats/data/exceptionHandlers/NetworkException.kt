package com.example.sportzstats.data.exceptionHandlers

class NetworkException(message: String, cause: Throwable? = null) : Exception(message, cause)

class HttpException(message: String, cause: Throwable? = null) : Exception(message, cause)

class UnknownException(message: String, cause: Throwable? = null) : Exception(message, cause)
