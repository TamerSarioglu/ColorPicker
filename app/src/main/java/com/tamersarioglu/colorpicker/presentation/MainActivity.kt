package com.tamersarioglu.colorpicker.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tamersarioglu.colorpicker.presentation.colorpalette.ColorPaletteScreen
import com.tamersarioglu.colorpicker.presentation.selectimage.SelectImageScreen
import com.tamersarioglu.colorpicker.presentation.ui.theme.ColorPickerTheme
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import androidx.core.net.toUri

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ColorPickerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.SelectImageScreen.route
                    ) {
                        composable(route = Screen.SelectImageScreen.route) {
                            SelectImageScreen(
                                onNavigateToColorPalette = { imageUri ->
                                    val encodedUri = URLEncoder.encode(
                                        imageUri.toString(),
                                        StandardCharsets.UTF_8.toString()
                                    )
                                    navController.navigate(
                                        Screen.ColorPaletteScreen.route + "/$encodedUri"
                                    )
                                }
                            )
                        }

                        composable(
                            route = Screen.ColorPaletteScreen.route + "/{imageUri}",
                            arguments = listOf(
                                navArgument("imageUri") {
                                    type = NavType.StringType
                                }
                            )
                        ) { backStackEntry ->
                            val encodedUri = backStackEntry.arguments?.getString("imageUri") ?: ""
                            val uri = java.net.URLDecoder.decode(
                                encodedUri,
                                StandardCharsets.UTF_8.toString()
                            ).toUri()
                            ColorPaletteScreen(imageUri = uri)
                        }
                    }
                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    data object SelectImageScreen : Screen("select_image_screen")
    data object ColorPaletteScreen : Screen("color_palette_screen")
}