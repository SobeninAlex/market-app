package com.example.utils

import android.content.Context
import android.content.res.Configuration
import androidx.compose.ui.graphics.Color
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.toArgb
import com.example.utils.resources.BackgroundCardColorDark
import com.example.utils.resources.BackgroundCardColorLight
import com.example.utils.resources.BlackColor

fun Context.setupSystemBarStyleDefault() {
    (this as ComponentActivity).enableEdgeToEdge(
        statusBarStyle = if ((resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES) {
            SystemBarStyle.dark(BackgroundCardColorDark.toArgb())
        } else {
            SystemBarStyle.light(BackgroundCardColorLight.toArgb(), BlackColor.toArgb())
        }
    )
}

fun Context.setupSystemBarStyleDark(color: Color) {
    (this as ComponentActivity).enableEdgeToEdge(
        statusBarStyle = SystemBarStyle.dark(color.toArgb())
    )
}

fun Context.setupSystemBarStyleLight(color: Color) {
    (this as ComponentActivity).enableEdgeToEdge(
        statusBarStyle = SystemBarStyle.light(color.toArgb(), BlackColor.toArgb())
    )
}