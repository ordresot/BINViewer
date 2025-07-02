package com.ordresot.binviewer.domain.api.usecase

import com.ordresot.binviewer.domain.model.BankCardInfo

interface GetSearchHistoryUseCase {
    suspend fun getSearchHistory(): List<BankCardInfo>
}