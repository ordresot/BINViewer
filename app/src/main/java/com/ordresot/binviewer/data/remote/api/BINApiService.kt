package com.ordresot.binviewer.data.remote.api

import com.ordresot.binviewer.data.remote.dto.SearchBINResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BINApiService {

    @GET("{bin}")
    suspend fun searchBIN(
        @Path("bin") bin: String
    ): Response<SearchBINResponse>

}