package com.example.utils.presentation

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import com.example.resources.GrayColor20
import com.example.resources.WhiteColor
import kotlinx.coroutines.delay

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick()
    }
}

fun Modifier.clickableOnce(onClick: () -> Unit): Modifier = composed(
    inspectorInfo = {
        name = "clickableOnce"
        value = onClick
    }
) {
    var enableAgain by remember { mutableStateOf(true) }
    LaunchedEffect(enableAgain) {
        if (enableAgain) return@LaunchedEffect
        delay(timeMillis = 500L)
        enableAgain = true
    }
    Modifier.clickable {
        if (enableAgain) {
            enableAgain = false
            onClick()
        }
    }
}

/**
 * если накладываешь эфект на компонент, то компоненту НЕ надо задавать background
 * если эффект наложен на контейнер в котором находятся эелемнты то:
 * //first = MaterialTheme.colorScheme.primaryContainer,
 * //second = MaterialTheme.colorScheme.outline,
 * если эффект наложен на сами элементы в контейнере то наоборот
 */
fun Modifier.shimmerEffect(
    isEnabled: Boolean = true,
    first: Color, //first = MaterialTheme.colorScheme.primaryContainer,
    second: Color, //second = MaterialTheme.colorScheme.outline,
): Modifier = composed {
    if (!isEnabled) return@composed Modifier

    var size by remember { mutableStateOf(IntSize.Zero) }

    val transition = rememberInfiniteTransition(label = "")

    val startOffsetX by transition.animateFloat(
        label = "",
        initialValue = -1f * size.width.toFloat(),
        targetValue = 1f * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200),
            repeatMode = RepeatMode.Restart
        )
    )

    background(
        brush = Brush.horizontalGradient(
            colors = listOf(
                first,
                second,
                first
            ),
            startX = startOffsetX,
            endX = startOffsetX + size.width.toFloat()
        )
    )
        .onGloballyPositioned {
            size = it.size
        }

    //TODO: оставил нга случай если нужен будет диагональный градиент
//    background(
//        brush = Brush.linearGradient(
//            colors = listOf(
//                WhiteColor,
//                GrayColor20,
//                WhiteColor
//            ),
//            start = Offset(startOffsetX, 0f),
//            end = Offset(startOffsetX + size.width.toFloat(), size.width.toFloat() / 3)
//        )
//    ).onGloballyPositioned {
//        size = it.size
//    }
}
