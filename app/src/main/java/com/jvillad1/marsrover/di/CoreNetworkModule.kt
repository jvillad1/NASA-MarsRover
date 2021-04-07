package com.jvillad1.marsrover.di

import com.jvillad1.marsrover.BuildConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

const val API_KEY = "api_key"

@Module
@InstallIn(SingletonComponent::class)
object CoreNetworkModule {

    @Provides
    fun providesMoshi(): Moshi = Moshi.Builder()
        .build()

    @Provides
    internal fun providesLoggingInterceptor(): HttpLoggingInterceptor? = when {
        BuildConfig.DEBUG -> {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }
        else -> null
    }

    @Provides
    internal fun providesBasicAuthorizationInterceptor(): Interceptor =
        Interceptor { chain ->
            val url = chain.request().url.newBuilder()
                .addQueryParameter(API_KEY, BuildConfig.NASA_API_KEY).build()
            val authenticatedRequest = chain.request().newBuilder().url(url).build()

            chain.proceed(authenticatedRequest)
        }

    @Provides
    internal fun providesOkHttpClientBuilder(
        loggingInterceptor: HttpLoggingInterceptor?
    ): OkHttpClient.Builder = OkHttpClient.Builder().apply {
        loggingInterceptor?.also { addInterceptor(it) }
    }

    @Provides
    internal fun providesBasicAuthorizationOkHttpClient(
        builder: OkHttpClient.Builder,
        authorizationInterceptor: Interceptor?
    ): OkHttpClient = with(builder) {
        authorizationInterceptor?.also {
            addInterceptor(it)
        }
        build()
    }
}
