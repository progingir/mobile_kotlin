package com.kotlin_yandex.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = GreenDarkTheme,
    onPrimary = Color.Black,

    onSecondary = Color.Black,
    tertiary = AccentGrey,
    onTertiary = Color.Black,

    background = AppDarkBackground,
    onBackground = AppWhite,
    surface = AppBlack,
    onSurface = AppWhite,
    surfaceVariant = AccentGrey,
    onError = Color.Black,
    error = ErrorColor
)

private val LightColorScheme = lightColorScheme(
    primary = GreenLightTheme,
    onPrimary = AppWhite,

    onSecondary = AppBlack,
    tertiary = AccentGrey,
    onTertiary = AppBlack,
    surfaceVariant = AccentGrey,

    background = AppOffWhite,
    onBackground = AppBlack,
    surface = AppWhite,
    onSurface = AppBlack,
)

@Composable
fun Kotlin_yandexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}