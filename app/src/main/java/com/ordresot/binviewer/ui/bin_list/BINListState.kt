package com.ordresot.binviewer.ui.bin_list

import com.ordresot.binviewer.domain.model.BankCardInfo

data class BINListState(
    val isLoading: Boolean = false,
    val data: List<BankCardInfo>? = null
)