package com.example.utils.presentation.compose

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.resources.MainColor
import com.example.resources.t2_Bold18

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopBar(
    modifier: Modifier = Modifier,
    title: String,
    goBack: (() -> Unit)? = null,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    navigationIconContentColor: Color = MainColor,
    titleContentColor: Color = MaterialTheme.colorScheme.onBackground,
    actionIconContentColor: Color = MainColor,
    actions: (@Composable () -> Unit)? = null,
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = t2_Bold18,
            )
        },
        navigationIcon = {
            goBack?.let { back ->
                NavigationTopBarIcon(onClick = back)
            }
        },
        actions = {
            actions?.invoke()
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor,
            navigationIconContentColor = navigationIconContentColor,
            titleContentColor = titleContentColor,
            actionIconContentColor = actionIconContentColor
        ),
        modifier = modifier
    )
}