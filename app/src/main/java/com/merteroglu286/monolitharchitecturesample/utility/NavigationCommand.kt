package com.merteroglu286.monolitharchitecturesample.utility

import androidx.navigation.NavDirections


sealed class NavigationCommand {
    data class ToDirection(val directions: NavDirections) : NavigationCommand()
    data object Back : NavigationCommand()
}