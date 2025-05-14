package com.plcoding.bookpedia.core.domain

sealed interface DataError : Error {
   enum class Remote : DataError {
       REQUEST_TIME_OUT,
       TO_MANY_REQUESTS,
       NO_INTERNET,
       SERVER,
       SERIALIZATION,
       UNKNOWN
   }

    enum class Local : DataError {
        DISK_FULL,
        UNKNOWN
    }
}