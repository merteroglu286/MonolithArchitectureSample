package com.merteroglu286.monolitharchitecturesample.presentation.viewmodel

import com.merteroglu286.monolitharchitecturesample.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class SharedViewModel @Inject constructor() : BaseViewModel() {
    val data = MutableStateFlow<String?>(null)
}
