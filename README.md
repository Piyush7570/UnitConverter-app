# 🔄 Unit Converter Android Application

A beautifully designed, premium-grade **Unit Converter Android Application** built using **Java** for core conversion logic, **XML** for modern Material 3 user interfaces, and **View Binding** for type-safe view interaction.

---

## ✨ Features

- **Modern Material 3 UI**: Implements a visually stunning card-based interface with premium Indigo-Teal gradients, rounded corners, custom dropdowns, and clean typography.
- **Dynamic Real-time Conversions**: Automatically updates conversion results as you type—no need to click a manual "Convert" button.
- **Micro-Animations**: Features a smooth 180° rotation animation on the unit swap button.
- **Comprehensive Unit Categories**:
  - 📏 **Length**: Millimeter (mm), Centimeter (cm), Meter (m), Kilometer (km), Inch (in), Foot (ft), Yard (yd), Mile (mi).
  - ⚖️ **Weight / Mass**: Milligram (mg), Gram (g), Kilogram (kg), Ounce (oz), Pound (lb).
  - 🌡️ **Temperature**: Celsius (°C), Fahrenheit (°F), Kelvin (K).
  - 🧪 **Volume**: Milliliter (ml), Liter (l), Gallon (gal), Cup (cup).
- **Quick Swap**: A dedicated swap button instantly exchanges your "From" and "To" units for effortless bidirectional conversion.

---

## 🛠️ Technologies & Libraries

- **Language**: Java 17
- **UI Architecture**: XML (AppCompat + ConstraintLayout)
- **Design System**: Google Material Design 3 Components
- **View Binding**: Android View Binding for safe UI coupling
- **Target SDK**: Android API 36 (minSdk 24)
- **Build System**: Gradle 9.1.0 with Kotlin DSL versions catalog

---

## 📂 Project Structure

```
app/src/main/
├── java/com/example/unitconverter/
│   ├── MainActivity.java     # UI coordination, event handling, animations
│   └── ConverterEngine.java  # Robust unit math logic & conversion equations
└── res/
    ├── drawable/             # Gradients, vector icons (ic_swap)
    ├── layout/               # activity_main.xml (modern UI layout)
    └── values/               # themes.xml (Material 3), colors.xml, strings.xml
```

---

## 🚀 Getting Started

### Prerequisites
- Android Studio (Jellyfish or newer recommended)
- Android SDK API 24+ (Android 7.0+)
- JDK 17

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/Piyush7570/UnitConverter-app.git
   ```
2. Open the project in **Android Studio**.
3. Let Gradle sync and download dependencies.
4. Run the project on an emulator or a physical device!
