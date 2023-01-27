package eu.benayoun.mylittlefoodquiz.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.Greeting
import eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.HomeScreen
import eu.benayoun.mylittlefoodquiz.ui.theme.MyLittleFoodQuizTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyLittleFoodQuizTheme {
                HomeScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyLittleFoodQuizTheme {
        Greeting("Android")
    }
}