package com.ordresot.binviewer.data.remote.api

import com.ordresot.binviewer.data.remote.dto.Request
import com.ordresot.binviewer.data.remote.dto.Response

interface NetworkClient {
    suspend fun makeRequest(dto: Request): Response
    fun isConnected(): Boolean
}