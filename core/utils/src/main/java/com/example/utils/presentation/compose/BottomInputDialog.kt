package com.example.utils.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.resources.MainColor
import com.example.resources.WhiteColor
import com.example.resources.roundedCornerShape12

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomInputDialog(
    value: String,
    onDismissRequest: () -> Unit,
    onClickApplyButton: (String) -> Unit,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentPadding: PaddingValues = PaddingValues(all = 16.dp),
    chips: LazyListScope.() -> Unit = {}
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        containerColor = containerColor,
        dragHandle = {},
        shape = RectangleShape
    ) {
        Column(
            modifier = Modifier.padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            var input by rememberSaveable { mutableStateOf(value) }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    EditTextLayout(
                        value = input,
                        onValueChange = {
                            input = it
                        }
                    )
                }

                Button(
                    modifier = Modifier.size(44.dp),
                    shape = roundedCornerShape12,
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MainColor,
                        contentColor = WhiteColor
                    ),
                    onClick = {
                        onClickApplyButton(input)
                        onDismissRequest()
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Outlined.Check,
                        contentDescription = null,
                    )
                }
            }

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                chips()
            }
        }
    }
}

@Preview
@Composable
private fun BottomInputDialogPreview() {
    BottomInputDialog(
        value = "sasdfsf",
        onDismissRequest = {},
        onClickApplyButton = {}
    )
}