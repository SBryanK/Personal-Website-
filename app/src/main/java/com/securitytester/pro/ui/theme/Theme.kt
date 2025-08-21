package com.securitytester.pro.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val SecurityTesterLightColorScheme = lightColorScheme(
    primary = SecurityBlue,
    onPrimary = PureWhite,
    primaryContainer = SecurityBlueLight,
    onPrimaryContainer = TextPrimary,
    
    secondary = AccentYellow,
    onSecondary = TextPrimary,
    secondaryContainer = AccentOrange,
    onSecondaryContainer = PureWhite,
    
    tertiary = AccentGreen,
    onTertiary = PureWhite,
    tertiaryContainer = SuccessGreen,
    onTertiaryContainer = PureWhite,
    
    error = ErrorRed,
    onError = PureWhite,
    errorContainer = AccentRed,
    onErrorContainer = PureWhite,
    
    background = BackgroundMain,
    onBackground = TextPrimary,
    surface = BackgroundCard,
    onSurface = TextPrimary,
    
    surfaceVariant = LightGray,
    onSurfaceVariant = TextSecondary,
    outline = MediumGray,
    outlineVariant = LightGray
)

private val SecurityTesterDarkColorScheme = darkColorScheme(
    primary = SecurityBlueLight,
    onPrimary = DarkBackground,
    primaryContainer = SecurityBlueDark,
    onPrimaryContainer = PureWhite,
    
    secondary = AccentYellow,
    onSecondary = DarkBackground,
    secondaryContainer = WarningOrange,
    onSecondaryContainer = PureWhite,
    
    tertiary = AccentGreen,
    onTertiary = DarkBackground,
    tertiaryContainer = SuccessGreen,
    onTertiaryContainer = PureWhite,
    
    error = AccentRed,
    onError = PureWhite,
    errorContainer = ErrorRed,
    onErrorContainer = PureWhite,
    
    background = DarkBackground,
    onBackground = DarkOnBackground,
    surface = DarkSurface,
    onSurface = DarkOnSurface
)

@Composable
fun SecurityTesterProTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> SecurityTesterDarkColorScheme
        else -> SecurityTesterLightColorScheme
    }
    
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = SecurityTesterTypography,
        content = content
    )
}
