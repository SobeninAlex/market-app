package com.example.utils.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.resources.roundedCornerShape12
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
    shape: Shape = roundedCornerShape20,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    paddingValues: PaddingValues = PaddingValues(all = 8.dp),
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(containerColor)
            .padding(paddingValues),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        Title(
            text = title,
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
