package com.ordresot.binviewer.domain.model

data class BankCardInfo(
    val scheme: String? = null ,
    val type: String? = null,
    val country: String? = null,
    val latitude: Int? = null,
    val longitude: Int? = null,
    val bankName: String? = null,
    val bankUrl: String? = null,
    val bankPhone: String? = null,
    val bankCity: String? = null
) {
    fun getSchemeUppercased(): String = scheme?.let { it.replaceFirstChar { it.uppercaseChar() } }.toString()
    fun getTypeUppercased(): String = type?.let { it.replaceFirstChar { it.uppercaseChar() } }.toString()
    fun getCoordinates(): String = "${latitude?.toString()}, ${longitude?.toString()}"
}