package com.tamersarioglu.colorpicker.presentation.colorpalette

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.tamersarioglu.colorpicker.presentation.common.components.ColorPaletteView
import com.tamersarioglu.colorpicker.presentation.common.components.LoadingIndicator

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
            .padding(16.dp)
    ) {
        // Image display
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = imageUri,
                contentDescription = "Selected image",
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
        }

        // Loading state
        LoadingIndicator(
            isLoading = state.isLoading,
            modifier = Modifier.fillMaxWidth()
        )

        // Error state
        if (state.error != null) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(vertical = 8.dp)
            )
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