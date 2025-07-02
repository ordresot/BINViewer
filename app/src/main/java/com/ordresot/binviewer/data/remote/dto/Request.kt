package com.ordresot.binviewer.data.remote.dto

sealed class Request {
    class SearchBINRequest(
        val query: String
    ): Request()
}