package com.merteroglu286.monolitharchitecturesample.data.repository

import com.merteroglu286.monolitharchitecturesample.data.mapper.toCharacterModel
import com.merteroglu286.monolitharchitecturesample.data.remote.ApiService
import com.merteroglu286.monolitharchitecturesample.domain.DataState
import com.merteroglu286.monolitharchitecturesample.domain.model.CharacterModel
import com.merteroglu286.monolitharchitecturesample.domain.repository.ApiRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class ApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ApiRepository {

    /*override suspend fun getCharacters(): DataState<CharactersModel> {
        return apiService.getCharacters().toDataState { responseData ->
            responseData?.toModel() ?: throw Exception("data is null")
        }
    }*/

    override suspend fun getCharacters(): DataState<List<CharacterModel>> {
        return DataState.Success(apiService.getCharacters().toCharacterModel())
    }


}
