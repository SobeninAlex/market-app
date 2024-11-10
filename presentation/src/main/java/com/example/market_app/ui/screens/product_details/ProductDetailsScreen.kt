package com.example.market_app.ui.screens.product_details

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.domain.entity.Product
import com.example.market_app.ui.theme.AccentColor
import com.example.utils.R
import com.example.market_app.ui.theme.BlackColor
import com.example.market_app.ui.theme.GrayColor50
import com.example.market_app.ui.theme.LightGrayColor30
import com.example.market_app.ui.theme.LightGrayColor50
import com.example.market_app.ui.theme.StarColor
import com.example.market_app.ui.theme.WhiteColor
import com.example.market_app.ui.theme.roundedCornerShape12
import com.example.market_app.ui.theme.roundedCornerShape16
import com.example.market_app.ui.theme.setupSystemBarStyleDefault
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductDetailsScreen(
    product: Product,
) {
    val context = LocalContext.current
    context.setupSystemBarStyleDefault(
        statusBarColor = Color.Transparent,
    )

    val viewModel = koinViewModel<ProductDetailsViewModel>()

    ProductDetailsScreenContent(product, onBackPressed = {}, onFavouriteClick = {})
}

@Composable
private fun ProductDetailsScreenContent(
    product: Product,
    onBackPressed: () -> Unit,
    onFavouriteClick: () -> Unit
) {
    Box {
        IconButton(
            modifier = Modifier
                .padding(start = 16.dp, top = 48.dp)
                .size(48.dp)
                .clip(CircleShape)
                .align(Alignment.TopStart)
                .zIndex(1f),
            onClick = onBackPressed,
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = LightGrayColor30
            )
        ) {
            Icon(
                modifier = Modifier.size(28.dp),
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "back",
                tint = BlackColor
            )
        }

        IconButton(
            modifier = Modifier
                .padding(end = 16.dp, top = 48.dp)
                .size(48.dp)
                .clip(CircleShape)
                .align(Alignment.TopEnd)
                .zIndex(1f),
            onClick = onFavouriteClick,
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = LightGrayColor30
            )
        ) {
            Icon(
                modifier = Modifier.size(28.dp),
                imageVector = Icons.Outlined.Favorite,
                contentDescription = "back",
                tint = GrayColor50
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        ) {
            item {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)),
                    model = product.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = product.title,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                            color = MaterialTheme.colorScheme.onBackground
                        )

                        Text(
                            text = product.priceString,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Star,
                            contentDescription = null,
                            tint = StarColor
                        )

                        Text(
                            text = "4.5", //todo
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
                            color = MaterialTheme.colorScheme.onBackground
                        )

                        Text(
                            text = "(10 Reviews)", //todo
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                    }

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = MaterialTheme.colorScheme.outline
                    )

                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Text(
                        text = product.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = MaterialTheme.colorScheme.outline
                    )

                    Text(
                        text = "Size",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        repeat(4) {
                            SizeCardItem(
                                size = "${it + 1}",
                                isSelected = it == 0,
                                onClick = {
                                    //todo
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(
                            onClick = {  },
                            shape = roundedCornerShape16,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = WhiteColor
                            ),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Buy Now",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                                color = AccentColor
                            )
                        }

                        Button(
                            onClick = {  },
                            shape = roundedCornerShape16,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = LightGrayColor50,
                            )
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_basket),
                                contentDescription = null,
                                tint = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SizeCardItem(
    size: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(roundedCornerShape12)
            .border(
                width = 2.dp,
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray,
                shape = roundedCornerShape12
            )
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = size,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
            color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun ProductDetailsScreen() {
    ProductDetailsScreenContent(Product.DEFAULT, onBackPressed = {}, onFavouriteClick = {})
}

















