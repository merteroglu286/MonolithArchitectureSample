package com.merteroglu286.monolitharchitecturesample.data.remote

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.merteroglu286.monolitharchitecturesample.domain.DataState
import com.merteroglu286.monolitharchitecturesample.utility.enums.ErrorPopUpType

@Keep
data class BaseResponse<T>(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: T?
)

fun <T, R> BaseResponse<T>.toDataState(mapper: (T?) -> R): DataState<R> {
    return when {
        code == 200 && data != null -> {
            DataState.Success(mapper(data))
        }
        code == 401 -> {
            DataState.Error(code, message ?: "", ErrorPopUpType.ERROR)
        }
        else -> {
            DataState.Error(code, message ?: "", ErrorPopUpType.ERROR)
        }
    }
}