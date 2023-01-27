package eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import eu.benayoun.mylittlefoodquiz.data.model.business.questions.FoodQuestion

@Composable
fun QuestionsListComposable(foodQuestionsList: List<FoodQuestion>, modifier: Modifier = Modifier) {
    val lazyState = rememberLazyListState()
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn(
            state = lazyState
        ) {
            items(foodQuestionsList) { foodQuestion ->
                FoodQuestionComposable(foodQuestion = foodQuestion)
            }
        }
    }
}