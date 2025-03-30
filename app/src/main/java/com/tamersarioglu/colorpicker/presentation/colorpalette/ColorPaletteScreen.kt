package com.tamersarioglu.colorpicker.presentation.colorpalette

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.tamersarioglu.colorpicker.presentation.common.components.ColorPaletteView
import com.tamersarioglu.colorpicker.presentation.common.components.LoadingIndicator
import com.tamersarioglu.colorpicker.presentation.ui.theme.*

@Composable
fun ColorPaletteScreen(
    imageUri: android.net.Uri,
    viewModel: ColorPaletteViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    LaunchedEffect(key1 = imageUri) {
        viewModel.onEvent(ColorPaletteEvent.LoadImage(imageUri))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Color Palette",
            style = MaterialTheme.typography.displayMedium,
            color = ColorPickerText,
            textAlign = TextAlign.Center
        )

        // Image display
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
                containerColor = ColorPickerSurface
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = imageUri,
                    contentDescription = "Selected image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(MaterialTheme.shapes.medium)
                )
            }
        }

        // Loading state
        LoadingIndicator(
            isLoading = state.isLoading,
            modifier = Modifier.fillMaxWidth()
        )

        // Error state
        if (state.error != null) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        // Color palette display
        state.colorPalette?.let { palette ->
            ColorPaletteView(
                colorPalette = palette,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}