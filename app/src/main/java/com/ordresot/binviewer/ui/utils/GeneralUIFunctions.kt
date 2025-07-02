package com.ordresot.binviewer.ui.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ordresot.binviewer.domain.model.BankCardInfo

@Composable
fun InfoRow(label: String, value: String?) {
    if (!value.isNullOrBlank()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, style = MaterialTheme.typography.bodyMedium)
            Text(text = value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun BankCardView(item: BankCardInfo){
    Card(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp)
    ) {
        Column(
            Modifier.fillMaxWidth().padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                InfoRow("Платежная система", item.getSchemeUppercased() )
                InfoRow("Тип", item.getTypeUppercased())
                InfoRow("Страна", item.country)
                InfoRow("Координаты", item.getCoordinates())
                InfoRow("Банк", item.bankName)
                InfoRow("Сайт", item.bankUrl)
                InfoRow("Телефон", item.bankPhone)
                InfoRow("Город", item.bankCity)
            }
        }
    }
}