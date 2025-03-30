package com.tamersarioglu.colorpicker.presentation.selectimage

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.tamersarioglu.colorpicker.presentation.common.PermissionHandler
import com.tamersarioglu.colorpicker.presentation.ui.theme.*

@Composable
fun SelectImageScreen(
    onNavigateToColorPalette: (Uri) -> Unit,
    viewModel: SelectImageViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    var showPermissionRequest by remember { mutableStateOf(false) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            viewModel.onEvent(SelectImageEvent.NavigateToColorPalette(it))
            onNavigateToColorPalette(it)
        }
    }

    if (showPermissionRequest) {
        PermissionHandler.RequestReadExternalStoragePermission(
            onPermissionGranted = {
                showPermissionRequest = false
                imagePickerLauncher.launch("image/*")
            },
            onPermissionDenied = {
                showPermissionRequest = false
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Select an Image",
            style = MaterialTheme.typography.displayMedium,
            color = ColorPickerText,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Choose an image to extract its color palette",
            style = MaterialTheme.typography.bodyLarge,
            color = ColorPickerSubtext,
            textAlign = TextAlign.Center
        )

        // Image preview box
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
                if (state.selectedImageUri != null) {
                    AsyncImage(
                        model = state.selectedImageUri,
                        contentDescription = "Selected image",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(MaterialTheme.shapes.medium)
                    )
                } else {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Image,
                            contentDescription = "Image placeholder",
                            modifier = Modifier.size(64.dp),
                            tint = ColorPickerSubtext
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "No image selected",
                            style = MaterialTheme.typography.bodyLarge,
                            color = ColorPickerSubtext
                        )
                    }
                }
            }
        }

        Button(
            onClick = {
                showPermissionRequest = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = ColorPickerAccent
            )
        ) {
            Text(
                text = "Select Image",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectImageScreenPreview() {
    SelectImageScreen(onNavigateToColorPalette = {})
}