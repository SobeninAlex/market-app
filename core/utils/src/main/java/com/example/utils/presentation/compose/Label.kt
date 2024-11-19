package com.example.utils.presentation.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.resources.body2_Reg14

@Composable
fun Label(text: String) {
    if (text.isBlank()) return
    Title(
        text = text,
        style = body2_Reg14,
        color = Color.Gray,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}
