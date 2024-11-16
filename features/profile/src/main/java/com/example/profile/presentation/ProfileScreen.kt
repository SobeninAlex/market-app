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
import androidx.compose.ui.res.stringResource
import com.example.resources.MainColor
import com.example.resources.R
import com.example.resources.WhiteColor
import com.example.utils.presentation.compose.SimpleTopBar
import com.example.utils.presentation.setupSystemBarStyleDefault
import com.example.utils.presentation.setupSystemBarStyleLight

@Composable
fun ProfileScreen() {
    val context = LocalContext.current
    context.setupSystemBarStyleDefault(
        statusBarColor = Color.Transparent
    )

    ProfileScreenContent()
}

@Composable
private fun ProfileScreenContent(

) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            SimpleTopBar(
                title = stringResource(R.string.title_tab_profile)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding())
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "ProfileScreen",
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}