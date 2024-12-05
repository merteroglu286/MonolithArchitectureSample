package com.merteroglu286.monolitharchitecturesample.presentation.fragment.dashboard

import com.merteroglu286.monolitharchitecturesample.domain.model.CharacterModel
import com.merteroglu286.monolitharchitecturesample.domain.usecase.ApiUseCase
import com.merteroglu286.monolitharchitecturesample.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class DashboardVM @Inject constructor(
    private val apiUseCase: ApiUseCase
) : BaseViewModel() {

    /**
     * Datayı sürekli canlı tutar fragment her açıldığında tetiklenir
     * It keeps the data alive and is triggered every time the fragment is opened.
     * */

    private val _characters = MutableStateFlow<List<CharacterModel>?>(emptyList())
    val characters = _characters.asStateFlow()

    /**
     * Only fragment is triggered once on first launch
     * Yanlızca fragment ilk açılışta bir kere tetiklenir
     * */

    /*private val _characters = MutableSharedFlow<List<CharacterModel>?>(null)
    val characters = _characters.asSharedFlow()*/



    fun getCharacters() {
        apiUseCase.execute(null).dataHandling(
            success = {
                _characters.value = it
            },
            error = {
                _characters.value = null
            }
        )
    }
}