package com.example.utils.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.resources.roundedCornerShape20
import com.example.resources.t3_Bold16

@Preview
@Composable
fun TitledBlockPreview() {
    TitledBlock(title = "Заголовок") {
        Spacer(Modifier.height(150.dp))
    }
}

@Composable
fun TitledBlock(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .clip(roundedCornerShape20)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(bottom = 16.dp)
    ) {
        Title(
            text = title,
            modifier = Modifier.padding(top = 16.dp, start = 16.dp)
        )
        content()
    }
}

@Composable
fun ClickableTitledBlock(
    title: String,
    modifier: Modifier = Modifier,
    clickEnabled: Boolean = true,
    onClick: () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .clip(roundedCornerShape20)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable(enabled = clickEnabled, onClick = onClick)
            .padding(bottom = 16.dp)
    ) {
        Title(
            text = title,
            modifier = Modifier.padding(top = 16.dp, start = 16.dp)
        )
        content()
    }
}

@Composable
fun Title(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = t3_Bold16,
    color: Color = MaterialTheme.colorScheme.onBackground
) {
    Text(
        text = text,
        style = style,
        color = color,
        modifier = modifier
    )
}
