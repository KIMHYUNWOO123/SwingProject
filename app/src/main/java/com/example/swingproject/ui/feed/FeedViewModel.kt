package com.example.swingproject.ui.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.entity.PhotoData
import com.example.domain.usecase.LocalUseCase
import com.example.domain.usecase.RemoteUseCase
import com.example.swingproject.BuildConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val remoteUseCase: RemoteUseCase,
    private val localUseCase: LocalUseCase,
) : ViewModel() {
    private val _photoPagingData = MutableStateFlow<PagingData<PhotoData>>(PagingData.empty())
    val photoPagingData: StateFlow<PagingData<PhotoData>> = _photoPagingData.asStateFlow()

    val photoFlowData: StateFlow<List<PhotoData>> = localUseCase.getAllFlow().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )
    fun getPhotos(query: String) {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, t ->
            Log.d("Error", "FeedViewModel - getPhotos(): ${t.message}")
        }) {
            remoteUseCase.getPhotos(query, BuildConfig.api_key).cachedIn(viewModelScope).collectLatest {
                _photoPagingData.value = it
            }
        }
    }

    fun clearPhoto() {
        _photoPagingData.value = PagingData.empty()
    }
    fun insertPhoto(photoData: PhotoData) {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, t ->
            Log.d("Error", "FeedViewModel - insertPhoto(): ${t.message}")
        }) {
            localUseCase.insertPhoto(photoData = photoData)
        }
    }

    fun deletePhoto(id: String) {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, t ->
            Log.d("Error", "FeedViewModel - deletePhoto(): ${t.message}")
        }) {
            localUseCase.deletePhoto(id)
        }
    }
}