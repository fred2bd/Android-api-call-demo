package com.example.myapplication.ui.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.BaseActivity
import com.example.myapplication.databinding.MainActivityBinding
import com.example.myapplication.model.api.Resource

import com.example.myapplication.viewmodel.ApiCallViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityBinding>(MainActivityBinding::inflate) {

    private val callApiViewModel: ApiCallViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        lifecycleScope.launch {
            callApiViewModel.callApi().collectLatest {
                when (it) {

                    is Resource.Loading -> {

                        if (it.showLoading) {
                            binding.progressBar!!.visibility = View.VISIBLE
                        } else {
                            binding.progressBar!!.visibility = View.GONE

                        }

                    }

                    is Resource.Success -> {
                        binding.dataTv!!.text = it.response!!.forecasts!![0]!!.toString()
                    }
                    is Resource.Error -> {
                        Log.d("ApiCallViewModel", it.message!!)
                    }

                    else -> {

                    }
                }
            }
        }
    }
}