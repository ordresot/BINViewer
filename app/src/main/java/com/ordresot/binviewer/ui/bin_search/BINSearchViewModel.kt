package com.ordresot.binviewer.ui.bin_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ordresot.binviewer.domain.api.usecase.GetBINInfoUseCase
import com.ordresot.binviewer.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class BINSearchViewModel @Inject constructor(
    getBINInfoUseCase: GetBINInfoUseCase
): ViewModel() {
    val searchQuery = MutableStateFlow("")

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<UIState> = searchQuery
        .debounce(2000)
        .filter { it.length == 6}
        .distinctUntilChanged()
        .flatMapLatest { searchQuery ->
            flow {
                emit(UIState(isLoading = true))
                when(val result = getBINInfoUseCase.getBINInfo(searchQuery)) {
                    is Resource.Success -> emit(UIState(data = result.data))
                    is Resource.Error -> emit(UIState(error = "Ошибка: ${result.message}"))
                }
            }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, UIState())
}