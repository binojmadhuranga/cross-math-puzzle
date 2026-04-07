# Cross Math Puzzle (Jetpack Compose)

A simple Android puzzle game built with Kotlin and Jetpack Compose.

Players solve math equations on a generated grid by filling editable blank cells. The app tracks score, supports a countdown timer, and provides easy navigation between Home and Game screens.

## Features

- Home screen with:
  - `New Game` (45 seconds)
  - `Advanced Level` (30 seconds)
  - `About` dialog
- Random puzzle generation for each game
- Editable blank cells only
- Equation validation with color feedback:
  - Green = correct
  - Red = incorrect
- Score tracking (`solved / total equations`)
- Timer shown at top-left in red
- Puzzle completion dialog with score
- `Refresh` button to generate a new random puzzle
- Android system back button support inside game screen

## Tech Stack

- Kotlin
- Jetpack Compose (Material 3)
- Android ViewModel (`lifecycle-viewmodel-compose`)
- Gradle Kotlin DSL

## Project Configuration

From the current Gradle setup:

- `compileSdk = 36`
- `targetSdk = 36`
- `minSdk = 24`
- Java/Kotlin target: `11`
- Android Gradle Plugin: `8.11.2`
- Kotlin: `2.0.21`
- Gradle Wrapper: `8.13`

## Project Structure

```text
app/src/main/java/com/example/cross_math_puzzle_compose/
  data/
    PuzzleGenerator.kt      # Creates random puzzle grids and equations
  model/
    Cell.kt                 # Grid cell model and color state enum
    Equation.kt             # Equation metadata (cells + answer)
    PuzzleState.kt          # Full puzzle UI state
  ui/
    component/
      GridCell.kt           # Individual cell rendering
    screen/
      GameScreen.kt         # Main game UI, timer, dialogs, interactions
      HomeScreen.kt         # Reusable home composable (if used)
    theme/
      ...                   # Compose theme files
  viewmodel/
    GameViewModel.kt        # State updates, scoring, equation checks
  MainActivity.kt           # App entry + in-activity screen switching
```

## Getting Started

### 1) Prerequisites

- Android Studio (latest stable recommended)
- Android SDK Platform 36 installed
- JDK 11 (usually bundled with Android Studio)
- An emulator or a physical Android device

### 2) Clone and Open

```powershell
git clone <your-repo-url>
cd puzzel
```

Open the folder in Android Studio and wait for Gradle sync to complete.

### 3) Run from Android Studio

1. Click **Run**.
2. Select an emulator or connected device.
3. The app launches as `com.example.cross_math_puzzle_compose`.

## Run on a Physical Android Device

1. On your phone, enable **Developer options**.
2. Enable **USB debugging**.
3. Connect phone with USB cable and allow the debugging prompt.
4. In Android Studio, choose the connected device and press **Run**.

Optional check from terminal:

```powershell
adb devices
```

If your phone appears as `device`, deployment should work.

## Build and Test (CLI)

From the project root:

```powershell
.\gradlew.bat clean
.\gradlew.bat assembleDebug
.\gradlew.bat testDebugUnitTest
```

Install debug APK to connected device:

```powershell
.\gradlew.bat installDebug
```

## How to Play

1. Start from Home:
   - `New Game` for a 45-second round
   - `Advanced Level` for a 30-second round
2. Tap an editable blank cell.
3. Enter a number and confirm.
4. Complete equations correctly to increase score.
5. Use `Refresh` to generate a new random puzzle.

## Troubleshooting

- **Device not shown in Android Studio**
  - Reconnect cable, enable USB debugging, confirm RSA prompt.
  - Run `adb kill-server` then `adb start-server`.
- **Gradle sync fails**
  - Ensure internet access and installed SDK 36.
  - Re-sync project and verify JDK is set to 11.
- **Build errors with versions**
  - Keep wrapper and plugin versions from the project files.
- **App installs but does not open**
  - Uninstall old app version and run again.

## Notes

- Puzzle grid size and equation count are randomized each game.
- Current puzzle generation includes addition and multiplication equations.

