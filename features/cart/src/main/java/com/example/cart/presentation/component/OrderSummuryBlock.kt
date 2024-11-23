package com.example.cart.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.domain.Summary
import com.example.resources.R
import com.example.resources.sub_Med14
import com.example.resources.t2_Bold18
import com.example.utils.presentation.compose.RoundedColumn
import com.example.utils.presentation.compose.TextPointsValue
import com.example.utils.presentation.compose.Title

fun LazyListScope.orderSummaryBlock(
    summary: Summary
) = item {
    RoundedColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Title(
            modifier = Modifier.padding(top = 16.dp, start = 16.dp, bottom = 16.dp),
            text = stringResource(R.string.title_order_summary_block),
            style = t2_Bold18,
        )

        TextPointsValue(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "Items",
            textStyle = sub_Med14,
            value = summary.items.size.toString(),
            valueStyle = sub_Med14,
            dashGap = 8.dp
        )

        HorizontalDivider(color = MaterialTheme.colorScheme.outline, modifier = Modifier.padding(vertical = 6.dp))

        TextPointsValue(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "Total",
            textStyle = sub_Med14,
            valueStyle = sub_Med14,
            value = summary.total.toString(),
            dashGap = 8.dp
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}