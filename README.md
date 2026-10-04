# Knife Steel Compare

A small, fully offline Android app for comparing knife steels side by side.

## Current comparison axes

- Toughness
- Edge retention
- Corrosion resistance
- Ease of sharpening
- Fine-edge stability
- Typical hardness range (HRC)
- Practical notes

The app currently includes 20 steels and uses an overlaid radar chart for quick visual comparison.

## Android

- Minimum Android: 8.0 (API 26)
- Target Android: Android 15 / API 35
- No Internet permission
- No analytics or ads

## Build

GitHub Actions builds a debug APK on every push to `main`. Open the latest **Build Android APK** workflow run and download the `KnifeSteelCompare-debug-apk` artifact.

The numeric scores are comparative estimates rather than laboratory constants. Heat treatment, blade geometry, edge angle, finish, and manufacturer implementation can materially change real-world performance.
