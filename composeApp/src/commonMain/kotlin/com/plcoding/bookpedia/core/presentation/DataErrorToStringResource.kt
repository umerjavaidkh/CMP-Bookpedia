package com.plcoding.bookpedia.core.presentation

import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.error_disk_full
import cmp_bookpedia.composeapp.generated.resources.error_no_internet
import cmp_bookpedia.composeapp.generated.resources.error_request_timeout
import cmp_bookpedia.composeapp.generated.resources.error_serialization
import cmp_bookpedia.composeapp.generated.resources.error_too_many_requests
import cmp_bookpedia.composeapp.generated.resources.error_unknown_error
import com.plcoding.bookpedia.core.domain.DataError

fun DataError.toUiText(): UiText {
    val stringResource = when (this) {
        DataError.Local.DISK_FULL -> Res.string.error_disk_full
        DataError.Local.UNKNOWN -> Res.string.error_unknown_error
        DataError.Remote.REQUEST_TIME_OUT -> Res.string.error_request_timeout
        DataError.Remote.TO_MANY_REQUESTS -> Res.string.error_too_many_requests
        DataError.Remote.NO_INTERNET -> Res.string.error_no_internet
        DataError.Remote.SERVER -> Res.string.error_unknown_error
        DataError.Remote.SERIALIZATION -> Res.string.error_serialization
        DataError.Remote.UNKNOWN -> Res.string.error_unknown_error
    }
    return UiText.StringResourceId(stringResource)
}