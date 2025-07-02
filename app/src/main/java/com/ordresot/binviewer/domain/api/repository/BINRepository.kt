package com.ordresot.binviewer.domain.api.repository

import com.ordresot.binviewer.domain.model.BankCardInfo
import com.ordresot.binviewer.utils.Resource

interface BINRepository {
    suspend fun getBankCardInfo(query: String): Resource<BankCardInfo>
    suspend fun getSearchHistory(): List<BankCardInfo>
    suspend fun updateSearchHistory(value: BankCardInfo)
}