package com.example.market_app.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun RoundedColumn(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    shape: Shape = RoundedCornerShape(size = 18.dp),
    contentPadding: PaddingValues = PaddingValues(horizontal = 0.dp, vertical = 16.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(backgroundColor)
            .padding(contentPadding),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        content()
    }
}

@Composable
fun ClickableRoundedColumn(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isClickEnabled: Boolean = true,
    backgroundColor: Color = Color.White,
    shape: Shape = RoundedCornerShape(size = 18.dp),
    contentPadding: PaddingValues = PaddingValues(horizontal = 0.dp, vertical = 16.dp),
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(backgroundColor)
            .clickable(enabled = isClickEnabled, onClick = onClick)
            .padding(contentPadding)
    ) {
        content()
    }
}
