package com.example.cart.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.resources.GrayColor10
import com.example.resources.MainColor
import com.example.resources.R
import com.example.resources.WhiteColor
import com.example.resources.roundedCornerShape16
import com.example.resources.roundedCornerShape4
import com.example.resources.t2_Bold18
import com.example.resources.t3_Bold16
import com.example.utils.domain.Cart
import com.example.utils.presentation.compose.ClickableRoundedColumn
import com.example.utils.presentation.compose.RoundedColumn
import com.example.utils.presentation.shimmerEffect

@Composable
fun CartItem(
    cart: Cart,
    onCartClick: (Cart) -> Unit
) {
    ClickableRoundedColumn(
        modifier = Modifier
            .height(110.dp)
            .fillMaxWidth(),
        onClick = { onCartClick(cart) },
        contentPadding = PaddingValues(all = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .width(126.dp)
                    .clip(roundedCornerShape16)
                    .background(color = MaterialTheme.colorScheme.outline)
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    model = cart.imageUrl,
                    contentDescription = null
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(start = 16.dp),
            ) {
                Column(
                    modifier = Modifier.align(Alignment.CenterStart),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(
                        text = cart.productName,
                        style = t3_Bold16,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Text(
                        text = "Apple",
                        style = t3_Bold16,
                        color = Color.Gray
                    )

                    Text(
                        text = cart.price.toString(),
                        style = t3_Bold16,
                        color = MainColor
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .weight(1.5f),
            ) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .clip(CircleShape)
                        .clickable { },
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = null,
                    tint = Color.Red
                )

                Counter(
                    modifier = Modifier.align(Alignment.BottomEnd),
                    value = cart.quantity.toString(),
                    onMinusClick = {},
                    onPlusClick = {}
                )
            }
        }
    }
}

@Composable
fun BoxScope.Counter(
    modifier: Modifier = Modifier,
    value: String,
    onMinusClick: () -> Unit,
    onPlusClick: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        IconButton(
            modifier = Modifier
                .clip(CircleShape)
                .background(color = MainColor)
                .size(28.dp),
            onClick = onMinusClick
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_minus),
                contentDescription = null,
                tint = WhiteColor
            )
        }

        Text(
            text = value,
            style = t2_Bold18,
            color = MaterialTheme.colorScheme.onBackground
        )

        IconButton(
            modifier = Modifier
                .clip(CircleShape)
                .background(color = MainColor)
                .size(28.dp),
            onClick = onPlusClick
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_plus),
                contentDescription = null,
                tint = WhiteColor
            )
        }
    }
}

@Composable
fun CartItemShimmer() {
    Box(
        modifier = Modifier
            .clip(roundedCornerShape16)
            .shimmerEffect()
            .height(110.dp)
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .width(126.dp)
                    .clip(roundedCornerShape16)
                    .background(color = MaterialTheme.colorScheme.outline)
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(start = 16.dp),
            ) {
                Column(
                    modifier = Modifier.align(Alignment.CenterStart),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .height(17.dp)
                            .clip(roundedCornerShape4)
                            .fillMaxWidth()
                            .background(color = MaterialTheme.colorScheme.outline)
                    )

                    Box(
                        modifier = Modifier
                            .height(17.dp)
                            .clip(roundedCornerShape4)
                            .fillMaxWidth(fraction = 0.5f)
                            .background(color = MaterialTheme.colorScheme.outline)
                    )

                    Box(
                        modifier = Modifier
                            .height(17.dp)
                            .clip(roundedCornerShape4)
                            .fillMaxWidth(fraction = 0.5f)
                            .background(color = MaterialTheme.colorScheme.outline)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .weight(1.5f),
            ) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(roundedCornerShape4)
                        .background(color = MaterialTheme.colorScheme.outline)
                        .align(Alignment.TopEnd)
                )

                CounterShimmer(
                    modifier = Modifier.align(Alignment.BottomEnd),
                )
            }
        }
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

@Preview
@Composable
private fun CartItemPreview() {
    CartItem(
        cart = Cart.FAKE,
        onCartClick = {}
    )
}

@Preview
@Composable
private fun CartItemShimmerPreview() {
    CartItemShimmer()
}






