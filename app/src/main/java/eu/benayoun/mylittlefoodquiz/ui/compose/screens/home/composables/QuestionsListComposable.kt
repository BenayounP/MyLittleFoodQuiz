package eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.pierrebenayoun.activityreport.ui.theme.BlueGrey300
import com.pierrebenayoun.activityreport.ui.theme.BlueGrey900
import eu.benayoun.mylittlefoodquiz.data.model.business.questions.FoodQuestion
import eu.benayoun.mylittlefoodquiz.ui.theme.BackgroundAndContentColor
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeColors
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeDimensions

@Composable
fun QuestionsListComposable(foodQuestionsList: List<FoodQuestion>, modifier: Modifier = Modifier) {
    val backgroundAndContentColor = BackgroundAndContentColor(
        ComposeColors.getColor(light = BlueGrey900, dark = BlueGrey300),
        ComposeColors.getColor(light = Color.White, dark = Color.White)
    )
    val lazyState = rememberLazyListState()
    Column(modifier = Modifier.background(backgroundAndContentColor.background)) {
        LazyColumn(
            state = lazyState, modifier = modifier
                .padding(horizontal = ComposeDimensions.padding1)
        ) {
            items(foodQuestionsList) { foodQuestion ->
                FoodQuestionComposable(foodQuestion = foodQuestion)
            }
        }
    }
}