package com.tamersarioglu.colorpicker.data.mapper

import androidx.palette.graphics.Palette
import android.net.Uri
import com.tamersarioglu.colorpicker.domain.model.Color
import com.tamersarioglu.colorpicker.domain.model.ColorPalette
import javax.inject.Inject

class ColorMapper @Inject constructor() {

    fun mapPaletteToColorPalette(palette: Palette, sourceUri: Uri): ColorPalette {
        val dominantSwatch = palette.dominantSwatch
        val colorList = mutableListOf<Color>()

        // Extract vibrant colors
        palette.vibrantSwatch?.let {
            colorList.add(mapSwatchToColor(it))
        }
        palette.lightVibrantSwatch?.let {
            colorList.add(mapSwatchToColor(it))
        }
        palette.darkVibrantSwatch?.let {
            colorList.add(mapSwatchToColor(it))
        }

        // Extract muted colors
        palette.mutedSwatch?.let {
            colorList.add(mapSwatchToColor(it))
        }
        palette.lightMutedSwatch?.let {
            colorList.add(mapSwatchToColor(it))
        }
        palette.darkMutedSwatch?.let {
            colorList.add(mapSwatchToColor(it))
        }

        // Filter out duplicates and null values
        val uniqueColors = colorList.distinctBy { it.hexCode }

        return ColorPalette(
            dominantColor = dominantSwatch?.let { mapSwatchToColor(it) }
                ?: Color("#000000", Triple(0, 0, 0)),
            colors = uniqueColors,
            sourceImageUri = sourceUri
        )
    }

    private fun mapSwatchToColor(swatch: Palette.Swatch): Color {
        val rgb = Triple(
            (swatch.rgb shr 16) and 0xFF,
            (swatch.rgb shr 8) and 0xFF,
            swatch.rgb and 0xFF
        )

        val hexCode = String.format(
            "#%02X%02X%02X",
            rgb.first,
            rgb.second,
            rgb.third
        )

        return Color(hexCode, rgb)
    }
}