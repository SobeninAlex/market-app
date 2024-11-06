package com.example.market_app.ui.screens.basket

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.market_app.ui.theme.MainColor
import com.example.market_app.ui.theme.setupSystemBarStyleDark

@Composable
fun BasketScreen() {
    val context = LocalContext.current
    context.setupSystemBarStyleDark(
        statusBarColor = MainColor,
        navigationBarColor = MainColor
    )

    BasketScreenContent()
}

@Composable
private fun BasketScreenContent(

) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "BasketScreen",
            color = MaterialTheme.colorScheme.primary
        )
    }
}