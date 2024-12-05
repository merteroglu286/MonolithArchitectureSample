package com.merteroglu286.monolitharchitecturesample.data.mapper

import com.merteroglu286.monolitharchitecturesample.data.remote.response.CharactersResponse
import com.merteroglu286.monolitharchitecturesample.domain.model.CharacterModel


fun CharactersResponse.toCharacterModel(): MutableList<CharacterModel> {
    val characterList = mutableListOf<CharacterModel>()
    this.results.forEach {
        characterList.add(
            CharacterModel(
                id = it.id,
                name = it.name,
                gender = it.gender,
                image = it.image
            )
        )
    }
    return characterList
}