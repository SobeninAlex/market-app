package com.example.utils.presentation.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.resources.R
import com.example.resources.body2_Reg14
import com.example.resources.t1_Med20

@Preview
@Composable
fun BlankScreenPreview() {
    BlankScreen(
        painter = painterResource(id = R.drawable.ic_no_document),
        title = stringResource(id = R.string.lorem),
        hint = stringResource(id = R.string.loremLong),
        visible = true
    )
}

@Composable
fun BlankScreen(
    painter: Painter,
    title: String,
    hint: String = "",
    modifier: Modifier = Modifier,
    visible: Boolean,
    actions: @Composable ColumnScope.() -> Unit = {}
) {
    val scrollState = rememberScrollState()

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(500)),
        exit = fadeOut(tween(500))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Icon(
                painter = painter,
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = title,
                style = t1_Med20,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 40.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = hint,
                style = body2_Reg14,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 40.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            actions()
        }
    }
}
