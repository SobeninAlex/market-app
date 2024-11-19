package com.example.utils.presentation.compose

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun EditTextLayout(
    value: String,
    onValueChange: (String) -> Unit,
    title: String = "",
    error: String = "",
    maxLength: Int = Int.MAX_VALUE,
    placeholder: String = "",
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Label(title)
    EditText(
        value = value,
        onValueChange = onValueChange,
        maxLength = maxLength,
        placeholder = placeholder,
        singleLine = singleLine,
        visualTransformation = visualTransformation,
        keyboardActions = KeyboardActions(
            onAny = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }
        ),
    )
    val showError = error.isNotBlank() && value.isNotBlank()
    AnimatedErrorText(
        errorText = error,
        isVisible = showError
    )
}
