package com.ordresot.binviewer.data.network.dto

sealed class Request {
    class SearchBINRequest(
        val query: String
    ): Request()
}