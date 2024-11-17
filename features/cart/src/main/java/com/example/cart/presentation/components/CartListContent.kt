package com.example.cart.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.resources.MainColor
import com.example.resources.WhiteColor
import com.example.resources.roundedCornerShape12
import com.example.resources.t3_Bold16
import com.example.utils.domain.Cart

@Composable
fun BoxScope.CartListContent(
    carts: List<Cart>,
    onCartClick: (Cart) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = carts,
            key = { it.id }
        ) { cart ->
            CartItem(
                cart = cart,
                onCartClick = onCartClick
            )
        }

        item {
            Spacer(modifier = Modifier.height(48.dp))
        }
    }

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .padding(16.dp),
        shape = roundedCornerShape12,
        colors = ButtonDefaults.buttonColors(
            containerColor = MainColor,
            contentColor = WhiteColor
        ),
        onClick = {}
    ) {
        Text(
            text = "Checkout",
            style = t3_Bold16
        )
    }
}

@Composable
fun CartListContentShimmer() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            count = 8
        ) {
            CartItemShimmer()
        }
    }
}