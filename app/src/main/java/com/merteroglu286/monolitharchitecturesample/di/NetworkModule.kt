package com.merteroglu286.monolitharchitecturesample.di

import android.content.Context
import com.merteroglu286.monolitharchitecturesample.data.local.Preferences
import com.merteroglu286.monolitharchitecturesample.data.remote.ApiService
import com.merteroglu286.monolitharchitecturesample.utility.constant.NetworkConstants.API_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    const val TIME_OUT = 300000L

    @Singleton
    @Provides
    fun provideOkhttpClient(
        @ApplicationContext context: Context,
        preferences: Preferences
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(Interceptor { chain ->
                val request: Request =
                    chain.request().newBuilder()
                        /**
                         * Bu, token gerektiren API'ler için gereklidir.
                         * This is required for APIs that require tokens.

                        .addHeader(
                            "Authorization",
                            "Bearer " + preferences.getAuthorizationToken().orEmpty()
                        )*/
                        .addHeader("Content-Type", "application/json")
                        .build()

                chain.proceed(request)
            })
            .build()
    }

    private val interceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonConverterFactory.create()
        return Retrofit.Builder().client(okHttpClient).baseUrl(API_URL)
            .addConverterFactory(gson).build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}