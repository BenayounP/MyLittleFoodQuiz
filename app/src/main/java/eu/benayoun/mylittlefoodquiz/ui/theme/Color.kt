package eu.benayoun.mylittlefoodquiz.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.pierrebenayoun.activityreport.ui.theme.Grey300
import com.pierrebenayoun.activityreport.ui.theme.Grey800

data class BackgroundAndContentColor(val background: Color, val content: Color)

class ComposeColors {
    companion object {

        @Composable
        fun textOnLightBackground() = Grey800

        @Composable
        fun textOnDarkBackground() = Grey300


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