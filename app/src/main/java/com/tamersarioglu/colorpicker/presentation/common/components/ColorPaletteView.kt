package com.tamersarioglu.colorpicker.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.tamersarioglu.colorpicker.presentation.ui.theme.ColorPickerAccent
import com.tamersarioglu.colorpicker.presentation.ui.theme.ColorPickerBorder
import com.tamersarioglu.colorpicker.presentation.ui.theme.ColorPickerSubtext
import com.tamersarioglu.colorpicker.presentation.ui.theme.ColorPickerSurface
import com.tamersarioglu.colorpicker.presentation.ui.theme.ColorPickerText
import com.tamersarioglu.colorpicker.domain.model.ColorPalette as DomainColorPalette

@Composable
fun ColorPaletteView(
    colorPalette: DomainColorPalette,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Color Palette",
            style = MaterialTheme.typography.titleLarge,
            color = ColorPickerText,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Dominant color
        ColorItem(
            color = Color(colorPalette.dominantColor.hexCode.toColorInt()),
            hexCode = colorPalette.dominantColor.hexCode,
            modifier = Modifier.padding(vertical = 8.dp),
            label = "Dominant Color"
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Color palette
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = ColorPickerSurface
            )
        ) {
            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                colorPalette.colors.forEach { color ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(
                                Color(color.hexCode.toColorInt())
                            )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Individual colors

        LazyColumn {
            items(colorPalette.colors.size) { index ->
                val color = colorPalette.colors[index]
                ColorItem(
                    color = Color(color.hexCode.toColorInt()),
                    hexCode = color.hexCode,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
fun ColorItem(
    color: Color,
    hexCode: String,
    modifier: Modifier = Modifier,
    label: String? = null
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = ColorPickerSurface
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(color)
                    .border(1.dp, ColorPickerBorder, MaterialTheme.shapes.medium)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                if (label != null) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelMedium,
                        color = ColorPickerSubtext
                    )
                }

                Text(
                    text = hexCode,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = ColorPickerText
                )
            }
        }
    }
}

@Composable
fun LoadingIndicator(
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    if (isLoading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                color = ColorPickerAccent
            )
        }
    }
}