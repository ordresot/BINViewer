package com.ordresot.binviewer.data.repository

import com.ordresot.binviewer.data.network.api.NetworkClient
import com.ordresot.binviewer.data.network.dto.Request
import com.ordresot.binviewer.data.network.dto.SearchBINResponse
import com.ordresot.binviewer.domain.api.repository.BINRepository
import com.ordresot.binviewer.domain.model.BankCardInfo
import com.ordresot.binviewer.utils.Resource
import javax.inject.Inject

class BINRepositoryImpl @Inject constructor(
    val client: NetworkClient
): BINRepository {
    override suspend fun getBankCardInfo(query: String): Resource<BankCardInfo> {
        val response = client.makeRequest(Request.SearchBINRequest(query))
        return when (response.resultCode) {
            200, 201 -> {
                val data = response as SearchBINResponse
                Resource.Success(
                    BankCardInfo(
                        scheme = data.scheme,
                        type = data.type,
                        country = data.country?.name,
                        latitude = data.country?.latitude,
                        longitude = data.country?.longitude,
                        bankName = data.bank?.name,
                        bankUrl = data.bank?.url,
                        bankPhone = data.bank?.phone,
                        bankCity = data.bank?.city
                    )
                )
            }
            404 -> {
                Resource.Error("Введенный BIN не найден")
            }
            10000 -> {
                Resource.Error("Проблемы с подключением к серверу")
            }
            else -> {
                Resource.Error("Неизвестная ошибка\nКод: ${response.resultCode}")
            }
        }
    }
}