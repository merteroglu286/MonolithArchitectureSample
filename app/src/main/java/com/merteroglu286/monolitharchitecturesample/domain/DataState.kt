package com.merteroglu286.monolitharchitecturesample.domain

import androidx.annotation.Keep
import com.merteroglu286.monolitharchitecturesample.utility.enums.ErrorPopUpType

@Keep
sealed class DataState<T> {
    data class Success<T>(val data: T) : DataState<T>()
    data class Loading<T>(val isLoading: Boolean) : DataState<T>()
    data class Error<T>(
        val errorCode: Int,
        val errorMessage: String,
        val errorPopUpType: ErrorPopUpType = ErrorPopUpType.ERROR
    ) : DataState<T>()
}