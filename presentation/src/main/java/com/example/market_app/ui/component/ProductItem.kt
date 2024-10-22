package com.example.market_app.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.domain.entity.Product

@Composable
fun ProductItem(
    product: Product
) {
    ClickableRoundedColumn(
        contentPadding = PaddingValues(all = 0.dp),
        onClick = { /*TODO*/ }
    ) {
        Text(
            text = product.toString()
        )
    }
}