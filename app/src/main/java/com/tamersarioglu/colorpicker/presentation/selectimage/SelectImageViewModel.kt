package com.tamersarioglu.colorpicker.presentation.selectimage

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectImageViewModel @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(SelectImageState())
    val state: State<SelectImageState> = _state

    fun onEvent(event: SelectImageEvent) {
        when (event) {
            is SelectImageEvent.NavigateToColorPalette -> {
                _state.value = _state.value.copy(
                    selectedImageUri = event.imageUri
                )
            }
        }
    }
}

data class SelectImageState(
    val selectedImageUri: Uri? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed class SelectImageEvent {
    data class NavigateToColorPalette(val imageUri: Uri) : SelectImageEvent()
}