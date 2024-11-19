package com.example.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.resources.BlackColor
import com.example.resources.GrayColor10
import com.example.resources.MainColor
import com.example.resources.R
import com.example.resources.WhiteColor
import com.example.resources.cap1_Med12
import com.example.resources.roundedCornerShape12
import com.example.resources.roundedCornerShape20
import com.example.resources.roundedCornerShape8
import com.example.resources.t3_Bold16
import com.example.utils.presentation.compose.BottomInputDialog
import com.example.utils.presentation.compose.EditTextLayout
import com.example.utils.presentation.compose.SimpleTopBar
import com.example.utils.presentation.compose.TextChips
import com.example.utils.presentation.noRippleClickable
import com.example.utils.presentation.setupSystemBarStyleDefault
import com.example.utils.presentation.setupSystemBarStyleLight

@Composable
fun ProfileScreen() {
    val context = LocalContext.current
    context.setupSystemBarStyleDefault(
        statusBarColor = Color.Transparent
    )

    ProfileScreenContent()
}

@Composable
private fun ProfileScreenContent(

) {
    Scaffold(
        modifier = Modifier.padding(bottom = 50.dp),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            SimpleTopBar(
                title = stringResource(R.string.title_tab_profile)
            )
        }
    ) { paddingValues ->
        val keyboardController = LocalSoftwareKeyboardController.current
        val focusManager = LocalFocusManager.current

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .noRippleClickable {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                },
            contentAlignment = Alignment.Center
        ) {
            val text = remember { mutableStateOf("") }
            var showDialog by rememberSaveable { mutableStateOf(false) }

            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EditTextLayout(
                    value = text.value,
                    onValueChange = {
                        text.value = it
                    }
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.5f),
                    shape = roundedCornerShape12,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MainColor,
                        contentColor = WhiteColor
                    ),
                    onClick = {
                        showDialog = true
                    }
                ) {
                    Text(
                        text = "show dialog",
                        style = t3_Bold16
                    )
                }
            }

            if (showDialog) {
                BottomInputDialog(
                    value = text.value,
                    onDismissRequest = { showDialog = false },
                    onClickApplyButton = { text.value = it },
                    chips = {
                        items(count = 12) {
                            val textChips = "text_${it}"
                            TextChips(
                                text = textChips,
                                clickEnabled = true,
                                onClick = {
                                    text.value = textChips
                                    showDialog = false
                                }
                            )
                        }
                    }
                )
            }

        }
    }
}
