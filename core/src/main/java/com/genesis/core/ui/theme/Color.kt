package com.genesis.core.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)
val Blue = Color(0xFF0073E6)
val Red = Color(0xFFE60000)
val LightGray = Color(0xFFD3D3D3)
val DarkGray = Color(0xFF808080)
val Transparent = Color(0x00000000)

// Renamed for clarity
val SurfaceContainerLight = Color(0xFFF0F0F0)
// Defined a new, proper color for dark theme cards
val SurfaceContainerDark = Color(0xFF2B2B2B)

val IndicatorColor = Color(0x516E6C6C) // Change this hex color value

val LightColorScheme = lightColorScheme(
    primary = Blue,
    onPrimary = White,
    secondary = LightGray,
    onSecondary = Black,
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Black,
    error = Red,
    onError = White,
    secondaryContainer = IndicatorColor,
    surfaceContainer = SurfaceContainerLight
)

val DarkColorScheme = darkColorScheme(
    primary = Blue,
    onPrimary = White,
    secondary = DarkGray,
    onSecondary = White,
    background = Black,
    onBackground = White,
    surface = Black,
    onSurface = White,
    error = Red,
    onError = White,
    secondaryContainer = IndicatorColor,
    // Use the new dark container color
    surfaceContainer = SurfaceContainerDark
)
