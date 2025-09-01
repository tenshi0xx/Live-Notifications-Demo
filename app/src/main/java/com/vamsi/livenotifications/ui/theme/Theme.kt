package com.vamsi.livenotifications.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme =
    darkColorScheme(
        primary = Color(0xFF6BB6FF),
        onPrimary = Color(0xFF001D36),
        primaryContainer = Color(0xFF0054A3),
        onPrimaryContainer = Color(0xFFD1E4FF),
        secondary = Color(0xFFBEC8DB),
        onSecondary = Color(0xFF283141),
        secondaryContainer = Color(0xFF3E4758),
        onSecondaryContainer = Color(0xFFDAE4F7),
        tertiary = Color(0xFFD6BFDD),
        onTertiary = Color(0xFF3B2947),
        tertiaryContainer = Color(0xFF523F5E),
        onTertiaryContainer = Color(0xFFF2DAFF),
        error = Color(0xFFFFB4AB),
        onError = Color(0xFF690005),
        errorContainer = Color(0xFF93000A),
        onErrorContainer = Color(0xFFFFDAD6),
        background = Color(0xFF121212),
        onBackground = Color(0xFFE3E2E6),
        surface = Color(0xFF121212),
        onSurface = Color(0xFFE3E2E6),
        surfaceVariant = Color(0xFF43474E),
        onSurfaceVariant = Color(0xFFC3C7CF),
        outline = Color(0xFF8D9199),
        outlineVariant = Color(0xFF43474E),
        scrim = Color(0xFF000000),
        inverseSurface = Color(0xFFE3E2E6),
        inverseOnSurface = Color(0xFF2F3033),
        inversePrimary = Color(0xFF0054A3),
    )

private val LightColorScheme =
    lightColorScheme(
        primary = Color(0xFF0054A3),
        onPrimary = Color(0xFFFFFFFF),
        primaryContainer = Color(0xFFD1E4FF),
        onPrimaryContainer = Color(0xFF001D36),
        secondary = Color(0xFF535F70),
        onSecondary = Color(0xFFFFFFFF),
        secondaryContainer = Color(0xFFD6E4F7),
        onSecondaryContainer = Color(0xFF101C2B),
        tertiary = Color(0xFF6B5778),
        onTertiary = Color(0xFFFFFFFF),
        tertiaryContainer = Color(0xFFF2DAFF),
        onTertiaryContainer = Color(0xFF251432),
        error = Color(0xFFBA1A1A),
        onError = Color(0xFFFFFFFF),
        errorContainer = Color(0xFFFFDAD6),
        onErrorContainer = Color(0xFF410002),
        background = Color(0xFFFCFCFF),
        onBackground = Color(0xFF1A1C1E),
        surface = Color(0xFFFCFCFF),
        onSurface = Color(0xFF1A1C1E),
        surfaceVariant = Color(0xFFDFE2EB),
        onSurfaceVariant = Color(0xFF43474E),
        outline = Color(0xFF73777F),
        outlineVariant = Color(0xFFC3C7CF),
        scrim = Color(0xFF000000),
        inverseSurface = Color(0xFF2F3033),
        inverseOnSurface = Color(0xFFF1F0F4),
        inversePrimary = Color(0xFF9ECAFF),
    )

@Composable
fun LiveNotificationsTheme(
    darkTheme: Boolean = true, // Default to dark theme
    dynamicColor: Boolean = false, // Disable dynamic colors for consistent appearance
    content: @Composable () -> Unit,
) {
    val colorScheme =
        when {
            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                val context = LocalContext.current
                if (darkTheme) dynamicDarkColorScheme(context)
                else dynamicLightColorScheme(context)
            }

            darkTheme -> DarkColorScheme
            else -> LightColorScheme
        }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.setDecorFitsSystemWindows(window, false)
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
