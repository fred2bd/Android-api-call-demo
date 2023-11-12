package com.roundright.androiddev.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.roundright.androiddev.api.ApiService
import com.roundright.androiddev.api.Repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        keyInterceptor: Interceptor

    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(keyInterceptor)
            .connectTimeout(
                10,
                TimeUnit.MINUTES
            ) //  timeout increased for sensor data sent for request size greater than 1 mb
            .writeTimeout(10, TimeUnit.MINUTES)
            .readTimeout(10, TimeUnit.MINUTES)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl("https://yahoo-weather5.p.rapidapi.com/")
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun providesRepository(apiService: ApiService) = Repository(apiService)

    @Singleton
    @Provides
    fun provideKeyInterceptor(): Interceptor =

        Interceptor {
            val request =
                it.request().newBuilder().addHeader("Accept", "application/json")
                    .addHeader(
                        "X-RapidAPI-Key", "c5de2d9566mshf2d360d7864245ap11d154jsnb431d81877c0"
                    ).addHeader(
                        "X-RapidAPI-Host", "yahoo-weather5.p.rapidapi.com"
                    ).build()
            it.proceed(request)
        }

    @Singleton
    @Provides
    fun provideGson(): Gson =
        GsonBuilder().setLenient().create()

}