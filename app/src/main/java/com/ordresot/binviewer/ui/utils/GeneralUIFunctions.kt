package com.ordresot.binviewer.ui.utils

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ordresot.binviewer.domain.model.BankCardInfo
import androidx.core.net.toUri

@Composable
fun InfoRow(label: String, value: String?, onClick: (()->Unit)? = null) {
    if (!value.isNullOrBlank()) {
        Row(
            modifier = Modifier.then(
                if (onClick != null) {
                    Modifier.fillMaxWidth().clickable {
                        onClick()
                    }
                } else {
                    Modifier.fillMaxWidth()
                }
            ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, style = MaterialTheme.typography.bodyMedium)
            Text(text = value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun BankCardView(item: BankCardInfo){
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
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
                InfoRow(
                    "Координаты",
                    item.getCoordinates(),
                    onClick = {
                        openLocation(
                            context,
                            item.latitude,
                            item.longitude
                        )
                    }
                )
                InfoRow("Банк", item.bankName)
                InfoRow(
                    "Сайт",
                    item.bankUrl,
                    onClick = {
                        openUrl(
                            context,
                            item.bankUrl.toString()
                        )
                    }
                )
                InfoRow(
                    "Телефон",
                    item.bankPhone,
                    onClick = {
                        callPhone(
                            context,
                            item.bankPhone.toString()
                        )
                    }
                )
                InfoRow("Город", item.bankCity)
            }
        }
    }
}

fun openLocation(context: Context, latitude: Double?, longitude: Double?) {
    val uri = "geo:$latitude,$longitude?q=$latitude,$longitude".toUri()
    val intent = Intent(Intent.ACTION_VIEW, uri)
    context.startActivity(intent)
}

fun callPhone(context: Context, phone: String) {
    val intent = Intent(Intent.ACTION_DIAL, "tel:$phone".toUri())
    context.startActivity(intent)
}

fun openUrl(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, "https://$url".toUri())
    context.startActivity(intent)
}