package com.ordresot.binviewer.domain.usecase

import com.ordresot.binviewer.domain.api.repository.BINRepository
import com.ordresot.binviewer.domain.api.usecase.UpdateSearchHistoryUseCase
import com.ordresot.binviewer.domain.model.BankCardInfo
import javax.inject.Inject

class UpdateSearchHistoryUseCaseImpl @Inject constructor(
    private val repository: BINRepository
): UpdateSearchHistoryUseCase {
    override suspend fun updateSearchHistory(value: BankCardInfo) {
        repository.updateSearchHistory(value)
    }
}