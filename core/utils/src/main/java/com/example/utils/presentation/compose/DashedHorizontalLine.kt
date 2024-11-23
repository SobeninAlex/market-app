package com.example.utils.presentation.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.resources.BlackColor
import com.example.resources.GrayColor
import com.example.resources.GrayColor50
import com.example.resources.cap2_Reg12

@Composable
fun DashedHorizontalLine(
    modifier: Modifier = Modifier,
    dashWidth: Dp = 2.dp,
    dashGap: Dp = 2.dp
) {
    Box(
        modifier = modifier
            .drawBehind {
                drawIntoCanvas { canvas ->
                    val paint = Paint().apply {
                        color = GrayColor50
                        style = PaintingStyle.Stroke
                        pathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(
                                dashWidth.toPx(),
                                dashGap.toPx()
                            ), 0f
                        )
                    }
                    val y = size.height / 2
                    canvas.drawLine(Offset(0f, y), Offset(size.width, y), paint)
                }
            }
    )
}

@Composable
fun TextPointsValue(
    modifier: Modifier = Modifier,
    text: String,
    value: String,
    textStyle: TextStyle = cap2_Reg12,
    textColor: Color = GrayColor,
    valueStyle: TextStyle = cap2_Reg12,
    valueColor: Color = MaterialTheme.colorScheme.onBackground,
    dashWidth: Dp = 2.dp,
    dashGap: Dp = 2.dp
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            modifier = Modifier.padding(end = 8.dp),
            text = text,
            style = textStyle,
            color = textColor
        )

        DashedHorizontalLine(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 3.dp),
            dashWidth = dashWidth,
            dashGap = dashGap
        )

        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = value,
            style = valueStyle,
            color = valueColor
        )
    }
}