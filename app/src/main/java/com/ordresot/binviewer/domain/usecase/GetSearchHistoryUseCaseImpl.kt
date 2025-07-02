package com.ordresot.binviewer.domain.usecase

import com.ordresot.binviewer.domain.api.repository.BINRepository
import com.ordresot.binviewer.domain.api.usecase.GetSearchHistoryUseCase
import com.ordresot.binviewer.domain.model.BankCardInfo
import javax.inject.Inject

class GetSearchHistoryUseCaseImpl @Inject constructor(
    private val repository: BINRepository
): GetSearchHistoryUseCase {
    override suspend fun getSearchHistory(): List<BankCardInfo> {
        return repository.getSearchHistory()
    }
}