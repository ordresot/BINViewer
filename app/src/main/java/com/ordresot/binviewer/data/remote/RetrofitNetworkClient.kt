package com.ordresot.binviewer.data.remote

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.ordresot.binviewer.data.remote.api.BINApiService
import com.ordresot.binviewer.data.remote.api.NetworkClient
import com.ordresot.binviewer.data.remote.dto.Request
import com.ordresot.binviewer.data.remote.dto.Response
import java.net.ConnectException
import javax.inject.Inject

class RetrofitNetworkClient @Inject constructor(
    private val binService: BINApiService,
    private val connectivityManager: ConnectivityManager
): NetworkClient {
    override suspend fun makeRequest(dto: Request): Response {
        if (!isConnected()) return Response().apply { resultCode = -1 }

        return try {
            when (dto) {
                is Request.SearchBINRequest -> {
                    val response = binService.searchBIN(dto.query)
                    return if (response.isSuccessful) {
                        response.body()?.apply { resultCode = response.code() }
                            ?: Response().apply { resultCode = response.code() }
                    } else {
                        Response().apply { resultCode = response.code() }
                    }
                }
            }
        } catch (e: ConnectException) {
            Response().apply { resultCode = 10000 }
        }
    }

    override fun isConnected(): Boolean {
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }
}