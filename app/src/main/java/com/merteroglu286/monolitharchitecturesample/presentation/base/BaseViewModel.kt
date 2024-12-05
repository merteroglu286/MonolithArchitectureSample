package com.merteroglu286.monolitharchitecturesample.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.merteroglu286.monolitharchitecturesample.domain.DataState
import com.merteroglu286.monolitharchitecturesample.utility.NavigationCommand
import com.merteroglu286.monolitharchitecturesample.utility.enums.ErrorPopUpType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
open class BaseViewModel : ViewModel() {

    private val _isLoading = Channel<Boolean>(Channel.BUFFERED)
    val isLoading = _isLoading.receiveAsFlow()

    private val _error = Channel<Triple<Int, String, ErrorPopUpType>>(Channel.BUFFERED)
    val error = _error.receiveAsFlow()

    private val _navigate = Channel<NavigationCommand>(Channel.BUFFERED)
    val navigate = _navigate.receiveAsFlow()


    fun <T> Flow<DataState<T>>.dataHandling(
        success: (data: T) -> Unit,
        error: () -> Unit = {},
        unAuth: () -> Unit = {},
    ): Job {
        return this.onEach {
            when (it) {
                is DataState.Success -> {
                    success(it.data)
                }

                is DataState.Loading -> {
                    _isLoading.send(it.isLoading)
                }

                is DataState.Error -> {
                    _error.send(Triple(it.errorCode, it.errorMessage, it.errorPopUpType))

                    if (it.errorCode == 401) {
                        unAuth()
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    protected fun navigate(navDirections: NavDirections) {
        viewModelScope.launch { _navigate.send(NavigationCommand.ToDirection(navDirections)) }
    }

    fun navigateBack() {
        viewModelScope.launch { _navigate.send(NavigationCommand.Back) }
    }

}