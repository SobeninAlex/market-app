package com.example.market_app.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.market_app.ui.theme.LightGray
import com.example.utils.R

@Composable
fun SearchBar(
    value: String,
    onTextChanged: (String) -> Unit,
) {
    TextField(
        value = value,
        onValueChange = onTextChanged,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(32.dp),
        leadingIcon = {
            Image(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = LightGray,
            unfocusedContainerColor = LightGray,
        ),
        placeholder = {
            Text(
                text = "Write name product",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    )
}