package com.merteroglu286.monolitharchitecturesample.domain

import com.merteroglu286.monolitharchitecturesample.utility.enums.ErrorPopUpType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart


@ExperimentalCoroutinesApi
abstract class BaseFlowUseCase<in In, Out> {

    private val TAG = "BaseFlowUseCase"

    open fun showLoading(): Boolean = true

    open fun errorPopupType(): ErrorPopUpType {
        return ErrorPopUpType.ERROR
    }

    protected abstract fun run(request: In?): Flow<DataState<Out>>

    open fun execute(request: In? = null) = run(request)
        .onStart {
            if (showLoading()) {
                emit(DataState.Loading(true))
            }
        }.onCompletion {
            emit(DataState.Loading(false))
        }
        .catch { t ->
            emit(DataState.Error(101, t.localizedMessage.orEmpty(), errorPopupType()))
        }.flowOn(Dispatchers.IO)

    inline fun <reified T> Flow<T>.setSuccess(): DataState.Success<Flow<T>> {
        return DataState.Success(this)
    }
}


