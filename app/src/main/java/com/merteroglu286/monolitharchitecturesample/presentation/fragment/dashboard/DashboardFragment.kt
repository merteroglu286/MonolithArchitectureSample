package com.merteroglu286.monolitharchitecturesample.presentation.fragment.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import com.merteroglu286.monolitharchitecturesample.databinding.FragmentDashboardBinding
import com.merteroglu286.monolitharchitecturesample.domain.model.CharacterModel
import com.merteroglu286.monolitharchitecturesample.presentation.adapter.CharacterAdapter
import com.merteroglu286.monolitharchitecturesample.presentation.base.BaseFragment
import com.merteroglu286.monolitharchitecturesample.utility.extension.myScope
import com.merteroglu286.monolitharchitecturesample.utility.extension.vertical
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardVM>() {


    private val adapter by lazy { CharacterAdapter(::characterAdapterHandle) }


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentDashboardBinding {
        return FragmentDashboardBinding.inflate(layoutInflater, container, false)
    }

    override fun initUI() {
        super.initUI()
        createAdapter()
    }

    override fun runOnce() {
        super.runOnce()
        viewModel.getCharacters()
    }

    private fun createAdapter() {
        binding.recyclerView.vertical(adapter)
    }

    override fun setReceivers() {
        super.setReceivers()

        with(viewModel) {
            characters.onEach(::handleCharacters).launchIn(myScope)
        }
    }

    private fun handleCharacters(users: List<CharacterModel>?) {
        adapter.submitList(users)
    }

    private fun characterAdapterHandle(event: CharacterAdapter.Event) {
        if (event is CharacterAdapter.Event.OnClickItem) {
//            showToast(event.item.name)
        }
    }
}