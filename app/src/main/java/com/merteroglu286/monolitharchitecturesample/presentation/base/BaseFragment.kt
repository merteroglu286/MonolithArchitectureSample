package com.merteroglu286.monolitharchitecturesample.presentation.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.merteroglu286.monolitharchitecturesample.utility.NavigationCommand
import com.merteroglu286.monolitharchitecturesample.utility.enums.ErrorPopUpType
import com.merteroglu286.monolitharchitecturesample.utility.extension.cast
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.lang.reflect.ParameterizedType

@ExperimentalCoroutinesApi
abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment() {

    abstract fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean = false
    ): VB


    lateinit var viewModel: VM

    private var _binding: VB? = null
    val binding get() = _binding!!

    val safeBinding: VB? = _binding


    private var activity: BaseActivity<*, *>? = null
    private var isCreated = false

    private var _locationPermissionGranted: (isGranted: Boolean) -> Unit = { _ -> }

    open fun isSavePageState(): Boolean {
        return false
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = requireActivity().cast()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val clazz = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1]
            .cast<Class<VM>>()

        if (!::viewModel.isInitialized) {
            viewModel = ViewModelProvider(this)[clazz]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (_binding != null && isSavePageState()) {
            return binding.root
        }
        _binding = getViewBinding(inflater, container)



        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("FRAGMENT PAGE STATE", "${this.javaClass.simpleName} onViewCreated")


        setListeners()

        setReceivers()

        initUI()

        if (!isCreated) {
            isCreated = true
            runOnce()
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()

        Log.e("FRAGMENT PAGE STATE", "${this.javaClass.simpleName} onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("FRAGMENT PAGE STATE", "${this.javaClass.simpleName} onPause")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (isSavePageState().not()) _binding = null
        Log.e("FRAGMENT PAGE STATE", "${this.javaClass.simpleName} onDestroyView")
    }

    open fun initUI() {}

    open fun setListeners() {}
    open fun runOnce() {}

    open fun setReceivers() {
        viewModel.navigate.onEach {
            when (it) {
                NavigationCommand.Back -> findNavController().navigateUp()
                is NavigationCommand.ToDirection -> findNavController().navigate(it.directions)
            }
        }.launchIn(lifecycleScope)

        viewModel.isLoading.onEach {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        }.launchIn(lifecycleScope)

        viewModel.error.onEach {
            hideLoading()
            when (it.third) {
                ErrorPopUpType.NONE -> Unit
                ErrorPopUpType.ERROR -> activity?.showErrorPopup(it.first, it.second)
            }
        }.launchIn(lifecycleScope)
    }


    fun showToast(message: String) {
        activity?.showToast(message)

    }

    fun showSuccessMessage(message: String, dismiss: () -> Unit) {
        activity?.showSuccessPopup(message, dismiss)
    }

    fun showErrorPopup(message: String) {
        activity?.showErrorPopup(101, message)
    }

    private fun showLoading() {
        activity?.showLoading()
    }

    private fun hideLoading() {
        activity?.hideLoading()
    }

}