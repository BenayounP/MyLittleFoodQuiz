package eu.benayoun.mylittlefoodquiz.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class BackgroundAndContentColor(val background: Color, val content: Color)

class ComposeColors {
    companion object {
        @Composable
        fun getColor(light: Color, dark: Color): Color {
            if (isDarkTheme()) {
                return dark
            } else {
                return light
            }
        }

        @Composable
        fun isDarkTheme() = isSystemInDarkTheme()
    }
}