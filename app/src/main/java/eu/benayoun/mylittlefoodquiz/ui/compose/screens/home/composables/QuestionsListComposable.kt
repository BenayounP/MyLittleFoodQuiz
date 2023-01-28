package eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pierrebenayoun.activityreport.ui.theme.transparent
import eu.benayoun.mylittlefoodquiz.data.model.business.questions.FoodQuestion
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeDimensions.padding2

@Composable
fun QuestionsListComposable(foodQuestionsList: List<FoodQuestion>, modifier: Modifier = Modifier) {
    val lazyState = rememberLazyListState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding2)
            .background(transparent)
    ) {
        LazyColumn(
            state = lazyState,
            verticalArrangement = Arrangement.spacedBy(padding2)
        ) {
            items(foodQuestionsList) { foodQuestion ->
                FoodQuestionComposable(foodQuestion = foodQuestion)
            }
        }
    }
}