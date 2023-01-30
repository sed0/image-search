package com.example.test.ui.mainscreen

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.data.repo.ImageRepository
import com.example.test.data.repo.NetworkImageRepository
import com.example.test.domain.entity.Image
import java.io.IOException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

sealed class MainScreenUiState {
    object Loading : MainScreenUiState()

    @Immutable
    data class Success(val images: List<Image>) : MainScreenUiState()
    data class Error(val errorMessage: String) : MainScreenUiState()
}

class MainScreenViewModel : ViewModel() {
    private val imageRepository: ImageRepository = NetworkImageRepository()

    private val _state: MutableStateFlow<MainScreenUiState> = MutableStateFlow(MainScreenUiState.Loading)
    val state = _state.asStateFlow()

    init {
        fetchImages()
    }

    fun fetchImages() {
        viewModelScope.launch {
            _state.value = MainScreenUiState.Loading

            _state.value = try {
                MainScreenUiState.Success(imageRepository.searchImages())
            } catch (e: HttpException) {
                MainScreenUiState.Error("Server error")
            } catch (e: IOException) {
                MainScreenUiState.Error("You're offline")
            }
        }
    }
}
