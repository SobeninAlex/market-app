package com.example.navigation

import androidx.annotation.StringRes
import com.example.resources.R
import kotlinx.collections.immutable.persistentListOf

data class AppTab(
    val icon: Int,
    @StringRes val labelRes: Int,
    val graph: Any
)

val MainTabs = persistentListOf(
    AppTab(
        icon = R.drawable.ic_home,
        labelRes = R.string.title_tab_home,
        graph = HomeGraph
    ),
    AppTab(
        icon = R.drawable.ic_basket,
        labelRes = R.string.title_tab_cart,
        graph = CartGraph
    ),
    AppTab(
        icon = R.drawable.ic_profile,
        labelRes = R.string.title_tab_profile,
        graph = ProfileGraph
    ),
)