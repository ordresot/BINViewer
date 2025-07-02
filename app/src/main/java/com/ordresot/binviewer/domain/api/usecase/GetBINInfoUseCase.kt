package com.ordresot.binviewer.domain.api.usecase

import com.ordresot.binviewer.domain.model.BankCardInfo
import com.ordresot.binviewer.utils.Resource

interface GetBINInfoUseCase {
    suspend fun getBINInfo(query: String): Resource<BankCardInfo>
}