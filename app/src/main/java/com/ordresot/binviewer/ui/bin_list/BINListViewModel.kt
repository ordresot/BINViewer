package com.ordresot.binviewer.ui.bin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ordresot.binviewer.domain.api.usecase.GetSearchHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class BINListViewModel @Inject constructor(
    private val getSearchHistoryUseCase: GetSearchHistoryUseCase
): ViewModel() {
    private val refreshTrigger = MutableStateFlow(Unit)

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<BINListState> = refreshTrigger
        .flatMapLatest {
            flow {
                emit(BINListState(isLoading = true))
                emit(BINListState(data = getSearchHistoryUseCase.getSearchHistory()))
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), BINListState())

    fun refresh() {
        refreshTrigger.value = Unit
    }
}