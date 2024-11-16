package com.example.utils.presentation.compose

import android.util.Log
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.resources.MainColor
import com.example.resources.R

@Preview
@Composable
fun NavigationTopBarIconPreview() {
    NavigationTopBarIcon(
        onClick = {}
    )
}

@Composable
fun NavigationTopBarIcon(
//    tint: Color = MainColor,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back_base),
            contentDescription = "Назад",
//            tint = tint
        )
    }
}
