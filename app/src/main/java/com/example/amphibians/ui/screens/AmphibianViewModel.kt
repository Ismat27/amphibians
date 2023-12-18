package com.example.amphibians.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmphibianApplication
import com.example.amphibians.data.IAmphibianRepository
import com.example.amphibians.network.Amphibian
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface AmphibianUiState {
    data class Success(val data: List<Amphibian>) : AmphibianUiState
    data object Error : AmphibianUiState
    data object Loading : AmphibianUiState
}

class AmphibianViewModel(private val amphibianRepository: IAmphibianRepository) : ViewModel() {

    var amphibianUiState: AmphibianUiState by mutableStateOf(AmphibianUiState.Error)
        private set

    init {
        getAmphibians()
    }

    fun getAmphibians() {
        amphibianUiState = AmphibianUiState.Loading
        viewModelScope.launch {
            amphibianUiState = try {
                val result = amphibianRepository.getAmphibians()
                AmphibianUiState.Success(result)
            } catch (e: IOException) {
                Log.d("AmphibianViewModel", e.message?: "error in getAmphibians")
                AmphibianUiState.Error
            } catch (e: HttpException) {
                Log.d("AmphibianViewModel", e.message?: "error in getAmphibians")
                AmphibianUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibianApplication)
                val amphibianRepository = application.container.amphibianRepository
                AmphibianViewModel(amphibianRepository = amphibianRepository)
            }
        }
    }

}