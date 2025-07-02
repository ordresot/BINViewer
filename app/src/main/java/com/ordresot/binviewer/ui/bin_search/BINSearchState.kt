package com.ordresot.binviewer.ui.bin_search

import com.ordresot.binviewer.domain.model.BankCardInfo

data class BINSearchState(
    val isLoading: Boolean = false,
    val data: BankCardInfo? = null,
    val error: String? = null
)