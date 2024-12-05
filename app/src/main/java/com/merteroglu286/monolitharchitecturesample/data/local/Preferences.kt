package com.merteroglu286.monolitharchitecturesample.data.local

interface Preferences {

    fun saveAuthorizationToken(authorizationToken: String)
    fun getAuthorizationToken(): String?

    fun saveUserId(id: Int)
    fun getUserId(): Int?

    fun saveLanguage(language:String)
    fun getLanguage(): String

}