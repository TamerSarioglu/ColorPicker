package com.tamersarioglu.colorpicker.domain.usecase

import android.net.Uri
import com.tamersarioglu.colorpicker.domain.model.ColorPalette
import com.tamersarioglu.colorpicker.domain.repository.ColorRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExtractColorsUseCase @Inject constructor(
    private val repository: ColorRepository
) {
    suspend operator fun invoke(imageUri: Uri): Flow<ColorPalette> {
        return repository.extractColorsFromImage(imageUri)
    }
}