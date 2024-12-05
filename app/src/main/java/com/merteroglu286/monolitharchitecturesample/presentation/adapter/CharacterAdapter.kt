package com.merteroglu286.monolitharchitecturesample.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.merteroglu286.monolitharchitecturesample.databinding.ItemCharacterBinding
import com.merteroglu286.monolitharchitecturesample.domain.model.CharacterModel
import com.merteroglu286.monolitharchitecturesample.presentation.base.BaseViewHolder

class CharacterAdapter(
    private val onEvent: (Event) -> Unit
) : ListAdapter<CharacterModel, CharacterAdapter.CharacterViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder.create(parent, onEvent)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CharacterViewHolder(
        private val binding: ItemCharacterBinding,
        private val onEvent: (Event) -> Unit
    ) : BaseViewHolder<CharacterModel>(binding.root) {

        override fun bind(item: CharacterModel) {
            with(binding) {
                binding.character = item
                binding.executePendingBindings()

                binding.root.setOnClickListener {
                    onEvent(Event.OnClickItem(item))
                }

            }
        }

        companion object {
            fun create(parent: ViewGroup, onEvent: (Event) -> Unit): CharacterViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemCharacterBinding.inflate(inflater, parent, false)
                return CharacterViewHolder(binding, onEvent)
            }
        }
    }

    /** DiffUtil kullanımı */
    private class DiffCallback : DiffUtil.ItemCallback<CharacterModel>() {

        override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem == newItem
        }
    }

    /** Sealed interface ile olay yönetimi */
    sealed interface Event {
        data class OnClickItem(val item: CharacterModel) : Event
    }
}
