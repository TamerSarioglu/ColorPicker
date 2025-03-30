package com.tamersarioglu.colorpicker.domain.usecase

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetImageUseCase @Inject constructor(
    private val context: Context
) {
    suspend operator fun invoke(uri: Uri): Result<Uri> = withContext(Dispatchers.IO) {
        try {
            // Validate the URI is accessible
            context.contentResolver.openInputStream(uri)?.use {
                // URI is valid and accessible
            }
            Result.success(uri)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}