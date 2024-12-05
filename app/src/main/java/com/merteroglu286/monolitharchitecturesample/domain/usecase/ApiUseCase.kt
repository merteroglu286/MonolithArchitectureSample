package com.merteroglu286.monolitharchitecturesample.domain.usecase

import android.util.Log
import com.merteroglu286.monolitharchitecturesample.domain.BaseFlowUseCase
import com.merteroglu286.monolitharchitecturesample.domain.DataState
import com.merteroglu286.monolitharchitecturesample.domain.model.CharacterModel
import com.merteroglu286.monolitharchitecturesample.domain.repository.ApiRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
class ApiUseCase @Inject constructor(
    private val apiRepository: ApiRepository
): BaseFlowUseCase<Unit,List<CharacterModel>>(){
    override fun run(request: Unit?): Flow<DataState<List<CharacterModel>>> = flow {
        val response = apiRepository.getCharacters()
        emit(response)
    }
}