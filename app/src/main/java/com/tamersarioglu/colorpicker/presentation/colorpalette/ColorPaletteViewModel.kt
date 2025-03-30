package com.tamersarioglu.colorpicker.presentation.colorpalette

import com.tamersarioglu.colorpicker.domain.model.ColorPalette
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tamersarioglu.colorpicker.domain.usecase.ExtractColorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.core.net.toUri

@HiltViewModel
class ColorPaletteViewModel @Inject constructor(
    private val extractColorsUseCase: ExtractColorsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(ColorPaletteState())
    val state: State<ColorPaletteState> = _state

    init {
        savedStateHandle.get<String>("imageUri")?.let { uriString ->
            val uri = uriString.toUri()
            extractColors(uri)
        }
    }

    fun onEvent(event: ColorPaletteEvent) {
        when (event) {
            is ColorPaletteEvent.LoadImage -> {
                extractColors(event.imageUri)
            }
        }
    }

    private fun extractColors(imageUri: android.net.Uri) {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoading = true,
                error = null
            )

            try {
                extractColorsUseCase(imageUri).onEach { palette ->
                    _state.value = _state.value.copy(
                        colorPalette = palette,
                        isLoading = false
                    )
                }.launchIn(viewModelScope)
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Unknown error occurred"
                )
            }
        }
    }
}

data class ColorPaletteState(
    val colorPalette: ColorPalette? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed class ColorPaletteEvent {
    data class LoadImage(val imageUri: android.net.Uri) : ColorPaletteEvent()
}
