package com.ordresot.binviewer.ui.bin_search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ordresot.binviewer.ui.utils.BankCardView
import com.ordresot.binviewer.ui.utils.InfoRow

@Composable
fun BINSearch(){
    val viewModel: BINSearchViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val queryText by viewModel.searchQuery.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(
            value = queryText,
            onValueChange = { if (it.length < 7) { viewModel.searchQuery.value = it } },
            placeholder = { Text("Первые шесть цифр номера карты") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        when {
            uiState.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            uiState.error != null -> Text(uiState.error!!, color = Color.Red)
            uiState.data != null -> {
                BankCardView(uiState.data!!)
            }
        }
    }
}