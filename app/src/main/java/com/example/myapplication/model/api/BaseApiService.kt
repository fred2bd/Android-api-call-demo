package com.example.myapplication.model.api

import android.util.Log

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException


fun <T> result(callApi: suspend () -> Response<T>): Flow<Resource<T?>> {
    return flow<Resource<T?>> {
        emit(Resource.Loading(true))
        delay(500)
        try {
            val response = callApi.invoke()
            response.let {

                if (response.isSuccessful) {
                    emit(Resource.Success(response.body()))

                } else {
                    emit(Resource.Error(response.message(), response.code()))
                }


            }
            delay(500)
            emit(Resource.Loading(false))

        } catch (t: Exception) {
            val errorMsg = t.message
            emit(Resource.Error(errorMsg))
            emit(Resource.Loading(false))

        }
    }.flowOn(Dispatchers.IO)
}

