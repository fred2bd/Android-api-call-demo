package com.example.myapplication.pref

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.viewbinding.BuildConfig


@SuppressLint("StaticFieldLeak")
object
PrefManager {

    var mInstance: PrefManager? = null
    private var mContext: Context? = null

    fun setInstance(context: Context?) {
        if (mInstance == null) {
            mContext = context
            mInstance = PrefManager
        }
    }

    fun getInstance(): PrefManager {
        return this.mInstance!!
    }


    fun updateValue(key: String, value: Any?) {
        val sharedPref: SharedPreferences =
            mContext!!.getSharedPreferences("package name", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit()

        when (value) {
            is String -> {
                editor.putString(key, value)

            }
            is Boolean -> {
                editor.putBoolean(key, value)

            }
            is Int -> {
                editor.putInt(key, value)

            }
            is Long -> {
                editor.putLong(key, value)

            }
            is Float -> {
                editor.putFloat(key, value)

            }
        }


        editor.apply()
    }



    fun getBool(key: String): Boolean {
        val sharedPref: SharedPreferences =
            mContext!!.getSharedPreferences("package name", Context.MODE_PRIVATE)
        return sharedPref.getBoolean(key, false)
    }

    fun getLong(key: String): Long {
        val sharedPref: SharedPreferences =
            mContext!!.getSharedPreferences("package name", Context.MODE_PRIVATE)
        return sharedPref.getLong(key, 0)
    }

    fun getString(key: String): String {
        val sharedPref: SharedPreferences =
            mContext!!.getSharedPreferences("package name", Context.MODE_PRIVATE)
        return sharedPref.getString(key, "")!!
    }

    fun getInt(key: String): Int {
        val sharedPref: SharedPreferences =
            mContext!!.getSharedPreferences("package name", Context.MODE_PRIVATE)
        return sharedPref.getInt(key, 0)
    }

    fun removeAllPrefData() {
        val sharedPref: SharedPreferences =
            mContext!!.getSharedPreferences("package name", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.clear().apply()
    }

}
