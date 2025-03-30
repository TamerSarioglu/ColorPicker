package com.tamersarioglu.colorpicker.domain.repository

import android.net.Uri
import com.tamersarioglu.colorpicker.domain.model.ColorPalette
import kotlinx.coroutines.flow.Flow

interface ColorRepository {
    suspend fun extractColorsFromImage(imageUri: Uri): Flow<ColorPalette>
    suspend fun saveColorPalette(colorPalette: ColorPalette)
    fun getRecentColorPalettes(): Flow<List<ColorPalette>>
}
