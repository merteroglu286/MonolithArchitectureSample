package com.merteroglu286.monolitharchitecturesample.data.remote

import com.merteroglu286.monolitharchitecturesample.data.remote.response.CharactersResponse
import retrofit2.http.GET

interface ApiService {

    @GET("character")
    suspend fun getCharacters(): CharactersResponse

}