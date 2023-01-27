package eu.benayoun.mylittlefoodquiz.ui.compose.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import com.pierrebenayoun.activityreport.ui.theme.Grey300
import com.pierrebenayoun.activityreport.ui.theme.Grey900
import eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.composables.QuestionsListComposable
import eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.model.HomeViewModel
import eu.benayoun.mylittlefoodquiz.ui.theme.BackgroundAndContentColor
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeColors

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val backgroundAndContentColor = BackgroundAndContentColor(
        ComposeColors.getColor(light = Grey300, dark = Grey900),
        ComposeColors.getColor(light = Grey900, dark = Grey300)
    )
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundAndContentColor.background
    ) {
        viewModel.ObserveLifecycle(LocalLifecycleOwner.current.lifecycle)
        val foodQuestionList = viewModel.questionListState.collectAsState().value
        QuestionsListComposable(foodQuestionsList = foodQuestionList)
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