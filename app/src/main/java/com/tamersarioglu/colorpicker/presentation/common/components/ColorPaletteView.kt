package com.tamersarioglu.colorpicker.presentation.common.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tamersarioglu.colorpicker.domain.model.ColorPalette as DomainColorPalette
import androidx.core.graphics.toColorInt

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
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Dominant color
        ColorItem(
            color = Color(colorPalette.dominantColor.hexCode.toColorInt()),
            hexCode = colorPalette.dominantColor.hexCode,
            modifier = Modifier.padding(vertical = 4.dp),
            label = "Dominant"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Color palette
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
        ) {
            colorPalette.colors.forEach { color ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(
                            Color(android.graphics.Color.parseColor(color.hexCode))
                        )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Individual colors
        colorPalette.colors.forEach { color ->
            ColorItem(
                color = Color(android.graphics.Color.parseColor(color.hexCode)),
                hexCode = color.hexCode,
                modifier = Modifier.padding(vertical = 4.dp)
            )
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
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(color, RoundedCornerShape(8.dp))
                .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            if (label != null) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelMedium
                )
            }

            Text(
                text = hexCode,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
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
            androidx.compose.material3.CircularProgressIndicator()
        }
    }
}