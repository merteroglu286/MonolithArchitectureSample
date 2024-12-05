package com.merteroglu286.monolitharchitecturesample.domain.repository

import com.merteroglu286.monolitharchitecturesample.domain.DataState
import com.merteroglu286.monolitharchitecturesample.domain.model.CharacterModel

interface ApiRepository {
    suspend fun getCharacters(): DataState<List<CharacterModel>>
}