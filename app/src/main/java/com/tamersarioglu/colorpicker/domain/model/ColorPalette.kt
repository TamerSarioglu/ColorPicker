package com.tamersarioglu.colorpicker.domain.model

import android.net.Uri

data class ColorPalette(
    val dominantColor: Color,
    val colors: List<Color>,
    val sourceImageUri: Uri?
)

data class Color(
    val hexCode: String,
    val rgb: Triple<Int, Int, Int>
)