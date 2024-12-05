package com.merteroglu286.monolitharchitecturesample.utility.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch


fun Any.hashCodeAsString(): String {
    return hashCode().toString()
}

inline fun <reified T : Any> Any.cast(): T {
    return this as T
}


fun Boolean.isTrue(valueTrue: () -> Unit): Boolean {
    if (this) {
        valueTrue()
    }
    return this
}

fun Boolean.isFalse(isFalse: () -> Unit): Boolean {
    if (!this) {
        isFalse()
    }
    return this
}


val Fragment.myScope: LifecycleCoroutineScope
    get() = this.viewLifecycleOwner.lifecycleScope


fun Fragment.myLaunch(launch: suspend () -> Unit) {
    this.viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch()
        }
    }
}