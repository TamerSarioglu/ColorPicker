package com.tamersarioglu.colorpicker.data.repository

import android.net.Uri
import com.tamersarioglu.colorpicker.data.mapper.ColorMapper
import com.tamersarioglu.colorpicker.data.source.ImageColorExtractor
import com.tamersarioglu.colorpicker.domain.model.ColorPalette
import com.tamersarioglu.colorpicker.domain.repository.ColorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ColorRepositoryImpl @Inject constructor(
    private val imageColorExtractor: ImageColorExtractor,
    private val colorMapper: ColorMapper
) : ColorRepository {

    // In-memory cache for recent palettes
    private val recentPalettes = mutableListOf<ColorPalette>()

    override suspend fun extractColorsFromImage(imageUri: Uri): Flow<ColorPalette> = flow {
        val palette = imageColorExtractor.extractColors(imageUri)
        val colorPalette = colorMapper.mapPaletteToColorPalette(palette, imageUri)
        emit(colorPalette)
    }

    override suspend fun saveColorPalette(colorPalette: ColorPalette) {
        // Add to recent palettes
        recentPalettes.add(0, colorPalette)

        // Keep only last 10 palettes
        if (recentPalettes.size > 10) {
            recentPalettes.removeAt(recentPalettes.lastIndex)
        }
    }

    override fun getRecentColorPalettes(): Flow<List<ColorPalette>> = flow {
        emit(recentPalettes.toList())
    }
}