package com.example.utils.presentation.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.resources.MainColor

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PullRefreshLayout(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    refreshContent: @Composable BoxScope.() -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    val pullRefreshState = rememberPullRefreshState(isRefreshing, onRefresh)

    Box(
        modifier = modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        AnimatedVisibility(
            visible = !isRefreshing,
            enter = fadeIn(tween(500)),
            exit = fadeOut(tween(500))
        ) {
            Box {
                content()
            }
        }

        AnimatedVisibility(
            visible = isRefreshing,
            enter = fadeIn(tween(500)),
            exit = fadeOut(tween(500))
        ) {
            Box {
                refreshContent()
            }
        }

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
            contentColor = MainColor,
            backgroundColor = MaterialTheme.colorScheme.primaryContainer
        )
    }
}
