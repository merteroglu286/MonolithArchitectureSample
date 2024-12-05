package com.merteroglu286.monolitharchitecturesample.presentation.activity.main

import android.os.Bundle
import com.merteroglu286.monolitharchitecturesample.data.local.Preferences
import com.merteroglu286.monolitharchitecturesample.databinding.ActivityMainBinding
import com.merteroglu286.monolitharchitecturesample.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainVM>() {

    @Inject
    lateinit var preferences: Preferences
    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}