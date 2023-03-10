package eu.benayoun.mylittlefoodquiz.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.HomeScreen
import eu.benayoun.mylittlefoodquiz.ui.theme.MyLittleFoodQuizTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyLittleFoodQuizTheme {
                HomeScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}