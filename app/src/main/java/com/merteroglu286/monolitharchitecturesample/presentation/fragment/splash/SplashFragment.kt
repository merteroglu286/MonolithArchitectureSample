package com.merteroglu286.monolitharchitecturesample.presentation.fragment.splash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.merteroglu286.monolitharchitecturesample.databinding.FragmentSplashBinding
import com.merteroglu286.monolitharchitecturesample.presentation.base.BaseFragment
import com.merteroglu286.monolitharchitecturesample.presentation.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import androidx.lifecycle.lifecycleScope
import com.merteroglu286.monolitharchitecturesample.utility.constant.AppConstants.SPLASH_DELAY
import com.merteroglu286.monolitharchitecturesample.utility.extension.myScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashVM>() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater, container, false)
    }

    override fun initUI() {
        super.initUI()
    }

    override fun runOnce() {
        super.runOnce()

        with(binding) {
            /*    lottieView.apply {
                    playAnimation()
                    addAnimatorListener(object : Animator.AnimatorListener {

                        override fun onAnimationStart(animation: Animator) {

                        }

                        override fun onAnimationEnd(animation: Animator) {

                        }

                        override fun onAnimationCancel(animation: Animator) {

                        }

                        override fun onAnimationRepeat(animation: Animator) {

                        }

                    })
                }*/

            lifecycleScope.launchWhenCreated {
                delay(SPLASH_DELAY)
                viewModel.goDashboardScreen()
            }
        }

    }

    override fun setReceivers() {
        super.setReceivers()

        sharedViewModel.data.filterNotNull().onEach { data ->
            showToast(data)
            sharedViewModel.data.value = null
        }.launchIn(myScope)
    }

}