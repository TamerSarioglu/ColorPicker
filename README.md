# ColorPicker

## 📱 Overview

ColorPicker is a modern Android application that extracts color palettes from any image on your device. Whether you're a designer looking for inspiration or a developer wanting to match your UI with specific image colors, ColorPicker provides an elegant solution with just a few taps.

## ✨ Features

- **Image Selection**: Choose any image from your gallery
- **Palette Extraction**: Automatically identifies dominant colors, vibrant and muted tones
- **Color Details**: View both hex codes and RGB values for each extracted color
- **Clean UI**: Modern, material-based interface built with Jetpack Compose
- **Color Previews**: Visualize each color with easy-to-view swatches

## 🏗️ Architecture

ColorPicker is built with modern Android development practices:

- **Clean Architecture**: Separation of concerns with domain, data, and presentation layers
- **MVVM Pattern**: ViewModels manage UI state and business logic
- **Dependency Injection**: Hilt for clean, testable code
- **Jetpack Compose**: Declarative UI built with Google's modern toolkit
- **Kotlin Coroutines**: For asynchronous operations and reactive programming

## 📚 Tech Stack

- **Kotlin**: 100% Kotlin codebase
- **Jetpack Compose**: Modern UI toolkit
- **Palette API**: For color extraction from images
- **Hilt**: Dependency injection
- **Coil**: Image loading
- **Navigation Compose**: For screen navigation
- **Material 3 Components**: For a consistent, beautiful UI

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog or newer
- Android SDK 24+
- Kotlin 2.1+

### Installation

1. Clone the repository:
```bash
git clone https://github.com/tamersarioglu/colorpicker.git
```

2. Open the project in Android Studio

3. Build and run on your device or emulator

## 🧩 Project Structure

```
├── app/
│   ├── data/               # Data layer
│   │   ├── mapper/         # Data mapping classes
│   │   ├── repository/     # Repository implementations
│   │   └── source/         # Data sources
│   ├── di/                 # Dependency injection
│   ├── domain/             # Domain layer
│   │   ├── model/          # Domain models
│   │   ├── repository/     # Repository interfaces
│   │   └── usecase/        # Use cases
│   └── presentation/       # UI layer
│       ├── colorpalette/   # Color palette screen
│       ├── common/         # Common UI components
│       ├── selectimage/    # Image selection screen
│       └── ui/             # Theme and styling
```

## 🔍 How It Works

1. **Select an Image**: The app requests permission to access your device's media gallery
2. **Extraction Process**: Once an image is selected, the app uses Android's Palette API to analyze the image
3. **Color Display**: The app presents the extracted colors in an easy-to-view format with both visual swatches and color codes

## 🧪 Future Enhancements

- Color palette saving and history
- Share palette as image or text
- Copy color codes to clipboard
- Extract colors from camera in real-time
- Material You theming support
- Dark mode
- Color naming based on common color standards

## 🤝 Contributing

Contributions are welcome! Feel free to open issues or submit pull requests.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 📧 Contact

Tamer Sarioglu - [GitHub Profile](https://github.com/tamersarioglu)

Project Link: [https://github.com/tamersarioglu/colorpicker](https://github.com/tamersarioglu/colorpicker)
