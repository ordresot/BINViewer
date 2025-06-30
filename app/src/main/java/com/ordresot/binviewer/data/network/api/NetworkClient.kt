package com.ordresot.binviewer.data.network.api

import com.ordresot.binviewer.data.network.dto.Request
import com.ordresot.binviewer.data.network.dto.Response

interface NetworkClient {
    suspend fun makeRequest(dto: Request): Response
    fun isConnected(): Boolean
}