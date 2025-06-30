package com.ordresot.binviewer.di

import android.content.Context
import android.net.ConnectivityManager
import com.ordresot.binviewer.data.network.RetrofitNetworkClient
import com.ordresot.binviewer.data.network.api.BINApiService
import com.ordresot.binviewer.data.network.api.NetworkClient
import com.ordresot.binviewer.data.repository.BINRepositoryImpl
import com.ordresot.binviewer.domain.api.repository.BINRepository
import com.ordresot.binviewer.domain.api.usecase.GetBINInfoUseCase
import com.ordresot.binviewer.domain.usecase.GetBINInfoUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBINApiService(): BINApiService {
        return Retrofit.Builder()
            .baseUrl("https://lookup.binlist.net")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BINApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideConnectivityManager(
        @ApplicationContext context: Context
    ): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    @Singleton
    fun provideNetworkClient(
        binService: BINApiService,
        connectivityManager: ConnectivityManager
    ): NetworkClient {
        return RetrofitNetworkClient(
            binService,
            connectivityManager
        )
    }

    @Provides
    @Singleton
    fun provideBINRepository(
        client: NetworkClient
    ): BINRepository {
        return BINRepositoryImpl(
            client
        )
    }

    @Provides
    @Singleton
    fun provideGetBINInfoUseCase(
        repository: BINRepository
    ): GetBINInfoUseCase {
        return GetBINInfoUseCaseImpl(
            repository
        )
    }
}