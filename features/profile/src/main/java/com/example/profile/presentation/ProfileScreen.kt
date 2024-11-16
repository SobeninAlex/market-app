package com.example.profile.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.utils.presentation.setupSystemBarStyleLight

@Composable
fun ProfileScreen() {
    val context = LocalContext.current
    context.setupSystemBarStyleLight(
        statusBarColor = Color.LightGray,
        navigationBarColor = Color.LightGray
    )

    ProfileScreenContent()
}

@Composable
private fun ProfileScreenContent(

) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "ProfileScreen",
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}