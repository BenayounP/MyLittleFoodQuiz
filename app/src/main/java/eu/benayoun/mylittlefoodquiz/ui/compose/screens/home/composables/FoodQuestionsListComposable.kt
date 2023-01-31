package eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.pierrebenayoun.activityreport.ui.theme.transparent
import eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.model.SelectableFoodQuestion
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeDimensions.padding2

@Composable
fun FoodQuestionsListComposable(
    modifier: Modifier = Modifier,
    foodQuestionsList: List<SelectableFoodQuestion>
) {
    // Question List
    Column(
        modifier = modifier
            .background(color = transparent)
            .clip(shape = RoundedCornerShape(padding2))
    ) {
            val lazyState = rememberLazyListState()
            LazyColumn(
                state = lazyState,
                verticalArrangement = Arrangement.spacedBy(padding2)
            ) {
                items(foodQuestionsList) { foodQuestion ->
                    FoodQuestionComposable(selectableFoodQuestion = foodQuestion)
                }
            }
    }
}
