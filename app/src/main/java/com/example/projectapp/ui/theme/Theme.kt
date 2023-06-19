package com.example.projectapp.ui.theme



import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorsZenith = lightColorScheme(
    primary = mdp_theme_light_primary,
    onPrimary = mdp_theme_light_onPrimary,
    primaryContainer = mdp_theme_light_primaryContainer,
    onPrimaryContainer = mdp_theme_light_onPrimaryContainer,
    secondary = mdp_theme_light_secondary,
    onSecondary = mdp_theme_light_onSecondary,
    secondaryContainer = mdp_theme_light_secondaryContainer,
    onSecondaryContainer = mdp_theme_light_onSecondaryContainer,
    tertiary = mdp_theme_light_tertiary,
    onTertiary = mdp_theme_light_onTertiary,
    tertiaryContainer = mdp_theme_light_tertiaryContainer,
    onTertiaryContainer = mdp_theme_light_onTertiaryContainer,
    error = mdp_theme_light_error,
    errorContainer = mdp_theme_light_errorContainer,
    onError = mdp_theme_light_onError,
    onErrorContainer = mdp_theme_light_onErrorContainer,
    background = mdp_theme_light_background,
    onBackground = mdp_theme_light_onBackground,
    surface = mdp_theme_light_surface,
    onSurface = mdp_theme_light_onSurface,
    surfaceVariant = mdp_theme_light_surfaceVariant,
    onSurfaceVariant = mdp_theme_light_onSurfaceVariant,
    outline = mdp_theme_light_outline,
    inverseOnSurface = mdp_theme_light_inverseOnSurface,
    inverseSurface = mdp_theme_light_inverseSurface,
    inversePrimary = mdp_theme_light_inversePrimary,
    surfaceTint = mdp_theme_light_surfaceTint,
    outlineVariant = mdp_theme_light_outlineVariant,
    scrim = mdp_theme_light_scrim,
)
private val DarkColorsZenith = darkColorScheme(
    primary = mdp_theme_dark_primary,
    onPrimary = mdp_theme_dark_onPrimary,
    primaryContainer = mdp_theme_dark_primaryContainer,
    onPrimaryContainer = mdp_theme_dark_onPrimaryContainer,
    secondary = mdp_theme_dark_secondary,
    onSecondary = mdp_theme_dark_onSecondary,
    secondaryContainer = mdp_theme_dark_secondaryContainer,
    onSecondaryContainer = mdp_theme_dark_onSecondaryContainer,
    tertiary = mdp_theme_dark_tertiary,
    onTertiary = mdp_theme_dark_onTertiary,
    tertiaryContainer = mdp_theme_dark_tertiaryContainer,
    onTertiaryContainer = mdp_theme_dark_onTertiaryContainer,
    error = mdp_theme_dark_error,
    errorContainer = mdp_theme_dark_errorContainer,
    onError = mdp_theme_dark_onError,
    onErrorContainer = mdp_theme_dark_onErrorContainer,
    background = mdp_theme_dark_background,
    onBackground = mdp_theme_dark_onBackground,
    surface = mdp_theme_dark_surface,
    onSurface = mdp_theme_dark_onSurface,
    surfaceVariant = mdp_theme_dark_surfaceVariant,
    onSurfaceVariant = mdp_theme_dark_onSurfaceVariant,
    outline = mdp_theme_dark_outline,
    inverseOnSurface = mdp_theme_dark_inverseOnSurface,
    inverseSurface = mdp_theme_dark_inverseSurface,
    inversePrimary = mdp_theme_dark_inversePrimary,
    surfaceTint = mdp_theme_dark_surfaceTint,
    outlineVariant = mdp_theme_dark_outlineVariant,
    scrim = mdp_theme_dark_scrim,
)

@Composable
//standard theme function
fun ProjectAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (!useDarkTheme) {
        LightColorsZenith
    } else {
        DarkColorsZenith
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}

