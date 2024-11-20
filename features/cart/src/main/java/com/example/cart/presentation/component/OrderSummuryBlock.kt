package com.example.cart.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.domain.Summary
import com.example.resources.R
import com.example.utils.presentation.compose.TitledBlock

fun LazyListScope.orderSummaryBlock(
    summary: Summary
) = item {
    TitledBlock(
        title = stringResource(R.string.title_order_summary_block),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {

    }
}