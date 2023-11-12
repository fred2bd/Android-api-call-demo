package com.example.myapplication.viewmodel

import android.util.Log
import com.example.myapplication.model.api.Resource
import com.example.myapplication.model.dataclass.Response
import com.roundright.androiddev.di.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class ApiCallViewModel @Inject constructor() : BaseViewModel() {

    fun callApi(): Flow<Resource<Response?>> {
        return repository.getApiResponse()
    }
}