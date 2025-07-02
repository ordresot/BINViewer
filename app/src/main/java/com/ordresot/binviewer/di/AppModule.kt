package com.ordresot.binviewer.di

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import com.google.gson.Gson
import com.ordresot.binviewer.data.local.SharedPrefsClient
import com.ordresot.binviewer.data.local.api.PreferenceClient
import com.ordresot.binviewer.data.remote.RetrofitNetworkClient
import com.ordresot.binviewer.data.remote.api.BINApiService
import com.ordresot.binviewer.data.remote.api.NetworkClient
import com.ordresot.binviewer.data.repository.BINRepositoryImpl
import com.ordresot.binviewer.domain.api.repository.BINRepository
import com.ordresot.binviewer.domain.api.usecase.GetBINInfoUseCase
import com.ordresot.binviewer.domain.api.usecase.GetSearchHistoryUseCase
import com.ordresot.binviewer.domain.api.usecase.UpdateSearchHistoryUseCase
import com.ordresot.binviewer.domain.usecase.GetBINInfoUseCaseImpl
import com.ordresot.binviewer.domain.usecase.GetSearchHistoryUseCaseImpl
import com.ordresot.binviewer.domain.usecase.UpdateSearchHistoryUseCaseImpl
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
    fun providePreferencesClient(
        sharedPreferences: SharedPreferences,
        gson: Gson
    ): PreferenceClient {
        return SharedPrefsClient(
            sharedPreferences,
            gson
        )
    }

    @Provides
    @Singleton
    fun provideBINRepository(
        networkClient: NetworkClient,
        preferencesClient: PreferenceClient
    ): BINRepository {
        return BINRepositoryImpl(
            networkClient,
            preferencesClient
        )
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences("bin_history", Context.MODE_PRIVATE)
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

    @Provides
    @Singleton
    fun provideGetSearchHistory(
        repository: BINRepository
    ): GetSearchHistoryUseCase {
        return GetSearchHistoryUseCaseImpl(
            repository
        )
    }

    @Provides
    @Singleton
    fun provideUpdateSearchHistory(
        repository: BINRepository
    ): UpdateSearchHistoryUseCase {
        return UpdateSearchHistoryUseCaseImpl(
            repository
        )
    }
}