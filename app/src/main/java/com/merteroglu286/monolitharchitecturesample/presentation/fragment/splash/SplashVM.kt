package com.merteroglu286.monolitharchitecturesample.presentation.fragment.splash

import com.merteroglu286.monolitharchitecturesample.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class SplashVM @Inject constructor() : BaseViewModel() {

    fun goDashboardScreen(){
        navigate(SplashFragmentDirections.actionSplashFragmentToDashboardFragment())
    }

}