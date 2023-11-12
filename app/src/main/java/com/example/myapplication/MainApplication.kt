package com.example.myapplication

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

import com.example.myapplication.pref.PrefManager

import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        PrefManager.setInstance(this)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        context = applicationContext
    }

    companion object {
        lateinit var context: Context

    }



}