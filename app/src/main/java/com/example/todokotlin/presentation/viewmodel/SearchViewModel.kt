package com.example.todokotlin.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todokotlin.data.models.Todo
import com.example.todokotlin.domain.usecase.searchusecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _searchKey = MutableStateFlow("")
    val searchKey: StateFlow<String> = _searchKey

    //gọi searchTodoLists mỗi khi searchQuery thay đổi
    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val todoList: StateFlow<List<Todo>> = _searchKey
        .debounce(300)
        .flatMapLatest { query ->
            searchUseCase.searchTodoLists(query)
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun updateSearchKey(key: String) {
        _searchKey.value = key
    }

}