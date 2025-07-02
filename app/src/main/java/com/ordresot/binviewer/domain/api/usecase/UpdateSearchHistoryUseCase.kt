package com.ordresot.binviewer.domain.api.usecase

import com.ordresot.binviewer.domain.model.BankCardInfo

interface UpdateSearchHistoryUseCase {
    suspend fun updateSearchHistory(value: BankCardInfo)
}