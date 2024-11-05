package com.example.market_app.ui.screens.basket

import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.example.utils.resources.MainColor
import com.example.utils.setupSystemBarStyleDark
import com.example.utils.setupSystemBarStyleLight

@Composable
fun BasketScreen() {
    val context = LocalContext.current
    context.setupSystemBarStyleDark(MainColor)

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