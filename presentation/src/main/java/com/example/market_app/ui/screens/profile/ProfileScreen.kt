package com.example.market_app.ui.screens.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.utils.setupSystemBarStyleDefault

@Composable
fun ProfileScreen() {
    val context = LocalContext.current
    context.setupSystemBarStyleDefault()

    ProfileScreenContent()
}

@Composable
private fun ProfileScreenContent(

) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "ProfileScreen",
            color = MaterialTheme.colorScheme.primary
        )
    }
}