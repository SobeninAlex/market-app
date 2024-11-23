package com.example.utils.presentation.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.resources.MainColor
import com.example.resources.WhiteColor
import com.example.resources.roundedCornerShape12
import com.example.resources.t3_Bold16

@Composable
fun SubmitButton(
    modifier: Modifier = Modifier,
    shape: Shape = roundedCornerShape12,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = MainColor,
        contentColor = WhiteColor
    ),
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = shape,
        colors = colors,
        onClick = onClick
    ) {
        content()
    }
}

@Composable
fun ApplyButton(
    modifier: Modifier = Modifier,
    shape: Shape = roundedCornerShape12,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = MainColor,
        contentColor = WhiteColor
    ),
    onClick: () -> Unit,
    text: String
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = shape,
        colors = colors,
        onClick = onClick
    ) {
        Text(
            text = text,
            style = t3_Bold16
        )
    }
}