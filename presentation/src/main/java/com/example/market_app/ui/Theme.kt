package com.example.market_app.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.utils.resources.AccentColor
import com.example.utils.resources.BackgroundCardColorDark
import com.example.utils.resources.BackgroundCardColorLight
import com.example.utils.resources.BackgroundColorDark
import com.example.utils.resources.BackgroundColorLight
import com.example.utils.resources.DividerColorDark
import com.example.utils.resources.DividerColorLight
import com.example.utils.resources.MainColor
import com.example.utils.resources.OnBackgroundColorDark
import com.example.utils.resources.OnBackgroundColorLight
import com.example.utils.resources.Shapes
import com.example.utils.resources.TextInputColorDark
import com.example.utils.resources.TextInputColorLight
import com.example.utils.resources.Typography

private val DarkColorScheme = darkColorScheme(
    primary = MainColor,
    secondary = AccentColor,
    background = BackgroundColorDark,
    primaryContainer = BackgroundCardColorDark,
    onBackground = OnBackgroundColorDark,
    outline = DividerColorDark,
    tertiaryContainer = TextInputColorDark,
)

private val LightColorScheme = lightColorScheme(
    primary = MainColor,
    secondary = AccentColor,
    background = BackgroundColorLight,
    primaryContainer = BackgroundCardColorLight,
    onBackground = OnBackgroundColorLight,
    outline = DividerColorLight,
    tertiaryContainer = TextInputColorLight,
)

@Composable
fun MarketappTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        shapes = Shapes,
        typography = Typography,
        content = content
    )
}