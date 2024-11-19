package com.example.utils.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.resources.GrayColor50
import com.example.resources.body1_Reg16
import com.example.resources.roundedCornerShape12
import com.example.resources.R
import com.example.utils.presentation.noRippleClickable

@Preview
@Composable
fun EditTextPreview() {
    EditText(value = "123", onValueChange = {})
}

@Composable
fun EditText(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    maxLength: Int = Int.MAX_VALUE,
    placeholder: String = "",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = body1_Reg16,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    cursorBrush: Brush = SolidColor(MaterialTheme.colorScheme.onBackground),
    closeIconVisibility: Boolean = true
) {
    BasicTextField(
        value = value,
        onValueChange = {
            if (isValidValue(it, maxLength)) onValueChange(it)
        },
        enabled = enabled,
        readOnly = readOnly,
        singleLine = singleLine,
        textStyle = textStyle.copy(color = MaterialTheme.colorScheme.onBackground),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout,
        interactionSource = interactionSource,
        cursorBrush = cursorBrush,
        visualTransformation = visualTransformation,
        decorationBox = { innerTextField ->
            // в многострочном редакторе выравниваем крестик (очистки поля) по верхнему краю
            // и нужно сдвинуть его чуть вниз, чтобы визуально казался по центру
            // когда еще не ввели несколько строк
            val verticalAlignment = if (singleLine) Alignment.CenterVertically else Alignment.Top
            val topIconPadding = if (singleLine) 0.dp else 1.dp
            Row(
                verticalAlignment = verticalAlignment,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = textStyle,
                            color = GrayColor50
                        )
                    }
                    innerTextField()
                }
                if (value.isNotEmpty() && closeIconVisibility) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "Очистить поле",
                        modifier = modifier
                            .padding(top = topIconPadding)
                            .size(20.dp)
                            .noRippleClickable { onValueChange("") }
                    )
                }
            }
        },
        modifier = modifier
            .clip(roundedCornerShape12)
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .padding(12.dp)
    )
}

private fun isValidValue(value: String, maxLength: Int): Boolean {
    return value.length <= maxLength
}
