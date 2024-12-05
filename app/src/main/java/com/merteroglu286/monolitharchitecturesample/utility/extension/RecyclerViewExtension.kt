package com.merteroglu286.monolitharchitecturesample.utility.extension


import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


fun Fragment.dividerDecorationVertical(@DrawableRes separatorDrawable: Int? = null): DividerItemDecoration {
    return DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
        separatorDrawable?.let {
            setDrawable(AppCompatResources.getDrawable(requireContext(), it)!!)
        }
    }

}

fun RecyclerView.vertical(
    adapter: RecyclerView.Adapter<*>,
    decoration: List<DividerItemDecoration> = listOf()
) {
    this.adapter = adapter
    this.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    decoration.forEach(this::addItemDecoration)


}
fun RecyclerView.horizontal(
    adapter: RecyclerView.Adapter<*>,
    decoration: List<DividerItemDecoration> = listOf()
) {
    this.adapter = adapter
    this.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    decoration.forEach(this::addItemDecoration)
}
