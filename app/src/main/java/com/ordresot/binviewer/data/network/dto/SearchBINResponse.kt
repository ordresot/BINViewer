package com.ordresot.binviewer.data.network.dto

data class SearchBINResponse(
    val number: NumberInfoDto?,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: CountryDto?,
    val bank: BankDto?
): Response()