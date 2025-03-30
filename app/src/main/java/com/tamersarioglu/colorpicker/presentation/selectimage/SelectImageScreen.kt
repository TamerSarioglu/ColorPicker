package com.tamersarioglu.colorpicker.presentation.selectimage

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.tamersarioglu.colorpicker.presentation.common.PermissionHandler

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
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Image preview box
        Box(
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            if (state.selectedImageUri != null) {
                AsyncImage(
                    model = state.selectedImageUri,
                    contentDescription = "Selected image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Text("image")
            }
        }

        Button(
            onClick = {
                showPermissionRequest = true
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text("Click to select")
        }
    }
}