package com.ordresot.binviewer.data.local.dto

import com.ordresot.binviewer.data.remote.dto.BankDto
import com.ordresot.binviewer.data.remote.dto.CountryDto
import com.ordresot.binviewer.data.remote.dto.NumberInfoDto

data class BINInfoDto(
    val scheme: String?,
    val type: String?,
    val country: String?,
    val latitude: Double?,
    val longitude: Double?,
    val bankName: String?,
    val bankUrl: String?,
    val bankPhone: String?,
    val bankCity: String?
)