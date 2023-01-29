package eu.benayoun.mylittlefoodquiz.ui.compose.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import com.pierrebenayoun.activityreport.ui.theme.*
import eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.composables.FoodQuestionsListComposable
import eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.model.HomeViewModel
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeColors

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel = hiltViewModel()) {
    @Composable
    fun backgroundGradientStart() = ComposeColors.getColor(light = Grey100, dark = Grey900)

    @Composable
    fun backgroundGradientEnd() = ComposeColors.getColor(light = Grey400, dark = Grey500)

    val backgroundBrush = Brush.linearGradient(
        colors = listOf(backgroundGradientStart(), backgroundGradientEnd())
    )
    Surface(
        modifier = modifier
            .fillMaxSize()
            .background(brush = backgroundBrush),
        color = transparent
    ) {
        viewModel.ObserveLifecycle(LocalLifecycleOwner.current.lifecycle)
        val foodQuestionList = viewModel.questionListState.collectAsState().value
        FoodQuestionsListComposable(
            foodQuestionsList = foodQuestionList,
            userHasRespondedAllQuestions = viewModel.userHasRespondedAllQuestions.value
        )
    }
}
//used to observe lifecycle like onResume and so on

@Composable
fun <LO : LifecycleObserver> LO.ObserveLifecycle(lifecycle: Lifecycle) {
    DisposableEffect(lifecycle) {
        lifecycle.addObserver(this@ObserveLifecycle)
        onDispose {
            lifecycle.removeObserver(this@ObserveLifecycle)
        }
    }
}