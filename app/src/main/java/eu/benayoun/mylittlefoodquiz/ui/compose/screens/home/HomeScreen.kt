package eu.benayoun.mylittlefoodquiz.ui.compose.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import com.pierrebenayoun.activityreport.ui.theme.Grey100
import com.pierrebenayoun.activityreport.ui.theme.Grey400
import com.pierrebenayoun.activityreport.ui.theme.Grey500
import com.pierrebenayoun.activityreport.ui.theme.Grey900
import eu.benayoun.mylittlefoodquiz.R
import eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.composables.FoodQuestionsListComposable
import eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.composables.SendResponsesComposable
import eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.composables.SimpleDialogComposable
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeColors
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeDimensions.padding2

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel = hiltViewModel()) {
    @Composable
    fun backgroundGradientStart() = ComposeColors.getColor(light = Grey100, dark = Grey900)

    @Composable
    fun backgroundGradientEnd() = ComposeColors.getColor(light = Grey400, dark = Grey500)

    val backgroundBrush = Brush.linearGradient(
        colors = listOf(backgroundGradientStart(), backgroundGradientEnd())
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(brush = backgroundBrush),
    ) {
        Spacer(modifier = Modifier.height(padding2))
        viewModel.ObserveLifecycle(LocalLifecycleOwner.current.lifecycle)
        val foodQuestionList = viewModel.questionListState.collectAsState().value

        FoodQuestionsListComposable(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = padding2),
            foodQuestionsList = foodQuestionList
        )
        Spacer(modifier = Modifier.height(padding2))
        SendResponsesComposable(
            modifier = Modifier.padding(horizontal = padding2),
            userHasRespondedAllQuestions = viewModel.userHasRespondedAllQuestionsState.value,
            sendResponses = viewModel::sendResponses
        )
        Spacer(modifier = Modifier.height(padding2))
    }

    // json response dialog
    val jsonString = viewModel.jsonResponseState.collectAsState().value
    if (jsonString != "") {
        SimpleDialogComposable(
            titleString = stringResource(R.string.dialog_json_title),
            TextString = jsonString,
            onClose = { viewModel.resetResponse() })
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