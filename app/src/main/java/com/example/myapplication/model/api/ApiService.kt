package com.roundright.androiddev.api

import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET
    suspend fun callApi(
        @Url url: String
    ): Response<com.example.myapplication.model.dataclass.Response>
}