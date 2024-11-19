package com.example.utils.presentation.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.resources.GrayColor50
import com.example.resources.MainColor
import com.example.resources.R
import com.example.resources.WhiteColor
import com.example.resources.roundedCornerShape4
import com.example.resources.t2_Bold18

@Composable
fun BoxScope.Counter(
    modifier: Modifier = Modifier,
    value: String,
    onMinusClick: () -> Unit,
    onPlusClick: () -> Unit,
    visibility: Boolean
) {

    AnimatedVisibility(
        modifier = modifier,
        visible = visibility,
        enter = fadeIn(tween(500)),
        exit = fadeOut(tween(500))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            IncrementButton(
                icon = painterResource(R.drawable.ic_minus),
                enabled = value.toInt() > 1,
                onClick = onMinusClick
            )

            Text(
                text = value,
                style = t2_Bold18,
                color = MaterialTheme.colorScheme.onBackground
            )

            IncrementButton(
                icon = painterResource(R.drawable.ic_plus),
                enabled = true,
                onClick = onPlusClick
            )
        }
    }
}

@Composable
fun IncrementButton(
    icon: Painter,
    enabled: Boolean,
    onClick: () -> Unit
) {
    IconButton(
        modifier = Modifier
            .clip(CircleShape)
            .size(28.dp),
        onClick = onClick,
        enabled = enabled,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MainColor,
            contentColor = WhiteColor,
            disabledContainerColor = GrayColor50,
            disabledContentColor = WhiteColor
        )
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
        )
    }
}

@Composable
fun BoxScope.CounterShimmer(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(color = MaterialTheme.colorScheme.outline)
                .size(28.dp),
        )

        Box(
            modifier = Modifier
                .height(22.dp)
                .width(18.dp)
                .clip(roundedCornerShape4)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.outline)
        )

        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(color = MaterialTheme.colorScheme.outline)
                .size(28.dp),
        )
    }
}