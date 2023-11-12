package com.roundright.androiddev.api

import com.example.myapplication.model.api.result


class Repository(private val apiService: ApiService) {


    fun getApiResponse() = result {
        apiService.callApi(url = "weather?woeid=2502265&format=json&u=f")

    }
}