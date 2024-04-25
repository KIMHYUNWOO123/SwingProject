package com.example.swingproject.ui.bookmark

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.PhotoData
import com.example.domain.usecase.LocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val localUseCase: LocalUseCase,
) : ViewModel() {
    val photoFlowData: StateFlow<List<PhotoData>> = localUseCase.getAllFlow().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )

    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    fun deletePhoto(id: String) {
        isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, t ->
            Log.d("BookmarkViewModel", "deletePhoto: ${t.message}")
        }) {
            localUseCase.deletePhoto(id)
        }.invokeOnCompletion {
            isLoading.postValue(false)
        }
    }
}