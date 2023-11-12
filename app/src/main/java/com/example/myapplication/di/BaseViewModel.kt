package com.roundright.androiddev.di

import androidx.lifecycle.ViewModel
import com.roundright.androiddev.api.Repository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


abstract class BaseViewModel : ViewModel() {

    @Inject
    protected lateinit var repository: Repository

}
