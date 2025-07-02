package com.ordresot.binviewer.data.repository

import com.ordresot.binviewer.data.local.api.PreferenceClient
import com.ordresot.binviewer.data.local.dto.BINHistoryPreference
import com.ordresot.binviewer.data.local.dto.BINInfoDto
import com.ordresot.binviewer.data.remote.api.NetworkClient
import com.ordresot.binviewer.data.remote.dto.Request
import com.ordresot.binviewer.data.remote.dto.SearchBINResponse
import com.ordresot.binviewer.domain.api.repository.BINRepository
import com.ordresot.binviewer.domain.model.BankCardInfo
import com.ordresot.binviewer.utils.Resource
import javax.inject.Inject

class BINRepositoryImpl @Inject constructor(
    val networkClient: NetworkClient,
    val sharedPrefsClient: PreferenceClient
): BINRepository {
    override suspend fun getBankCardInfo(query: String): Resource<BankCardInfo> {
        val response = networkClient.makeRequest(Request.SearchBINRequest(query))
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

    override suspend fun getSearchHistory(): List<BankCardInfo> {
        return (sharedPrefsClient.getData(BINHistoryPreference()) as? List<BINInfoDto> ?: emptyList()).map {
            BankCardInfo(
                scheme = it.scheme,
                type = it.type,
                country = it.country,
                latitude = it.latitude,
                longitude = it.longitude,
                bankName = it.bankName,
                bankUrl = it.bankUrl,
                bankPhone = it.bankPhone,
                bankCity = it.bankCity
            )
        }
    }

    override suspend fun updateSearchHistory(value: BankCardInfo) {
        val history = getSearchHistory().plus(value)
        sharedPrefsClient.saveData(
            BINHistoryPreference(
                history.map {
                    BINInfoDto(
                        scheme = it.scheme,
                        type = it.type,
                        country = it.country,
                        latitude = it.latitude,
                        longitude = it.longitude,
                        bankName = it.bankName,
                        bankUrl = it.bankUrl,
                        bankPhone = it.bankPhone,
                        bankCity = it.bankCity
                    )
                }
            )
        )
    }
}