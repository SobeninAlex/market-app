package com.example.market_app.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.domain.entity.Product

@Composable
fun ProductItem(
    product: Product
) {
    ClickableRoundedColumn(
        onClick = {}
    ) {
        Text(
            text = product.toString()
        )
    }
}