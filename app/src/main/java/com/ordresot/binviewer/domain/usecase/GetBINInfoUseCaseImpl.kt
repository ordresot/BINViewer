package com.ordresot.binviewer.domain.usecase

import com.ordresot.binviewer.domain.api.repository.BINRepository
import com.ordresot.binviewer.domain.api.usecase.GetBINInfoUseCase
import com.ordresot.binviewer.domain.model.BankCardInfo
import com.ordresot.binviewer.utils.Resource
import javax.inject.Inject

class GetBINInfoUseCaseImpl @Inject constructor(
    val repository: BINRepository
): GetBINInfoUseCase {
    override suspend fun getBINInfo(query: String): Resource<BankCardInfo> {
        return repository.getBankCardInfo(query)
    }
}