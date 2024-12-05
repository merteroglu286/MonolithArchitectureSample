package com.merteroglu286.monolitharchitecturesample.data.local

import android.content.Context
import android.content.SharedPreferences
import java.util.Locale
import javax.inject.Inject

class PreferencesImpl @Inject constructor(context: Context) : Preferences {

    private val AUTHORIZATON_PREF_HELPER = "authorization_pref"
    private val KEY_USER_ID = "key_user_id"
    private val KEY_LANGUAGE = "key_language"

    var mPrefs: SharedPreferences = context.getSharedPreferences("Pref", Context.MODE_PRIVATE)


    override fun saveAuthorizationToken(authorizationToken: String) {
        mPrefs.edit().putString(AUTHORIZATON_PREF_HELPER, authorizationToken).apply()
    }

    override fun getAuthorizationToken(): String {
        return mPrefs.getString(AUTHORIZATON_PREF_HELPER, "").orEmpty()
    }

    override fun saveUserId(id: Int) {
        mPrefs.edit().putInt(KEY_USER_ID, id).apply()
    }

    override fun getUserId(): Int {
        return mPrefs.getInt(KEY_USER_ID, -1)
    }

    override fun saveLanguage(language: String) {
        mPrefs.edit().putString(KEY_LANGUAGE, language).apply()
    }

    override fun getLanguage(): String {
        return mPrefs.getString(KEY_LANGUAGE, Locale.getDefault().language)
            ?: Locale.getDefault().language
    }
}