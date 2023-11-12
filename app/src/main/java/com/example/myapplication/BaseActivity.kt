package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.viewbinding.ViewBinding


abstract class BaseActivity<V : ViewBinding>(val bindingFactory: (LayoutInflater) -> V) :AppCompatActivity() {
    private lateinit var _binding: V
    protected val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingFactory(layoutInflater)
        setContentView(_binding.root)

    }

}
