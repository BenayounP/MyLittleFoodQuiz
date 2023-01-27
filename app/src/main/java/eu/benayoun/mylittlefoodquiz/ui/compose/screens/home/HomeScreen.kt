package eu.benayoun.mylittlefoodquiz.ui.compose.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.composables.QuestionsListComposable
import eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.model.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
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