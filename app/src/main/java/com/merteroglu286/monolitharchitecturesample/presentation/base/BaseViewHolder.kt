package com.merteroglu286.monolitharchitecturesample.presentation.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(val view: View) : RecyclerView.ViewHolder(view) {

    val context: Context
        get() = view.context

    open fun bind(item: T) = Unit

}

val ViewGroup.inflate: LayoutInflater
get() =  LayoutInflater.from(this.context)
