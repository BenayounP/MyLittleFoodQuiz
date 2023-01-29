package eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.pierrebenayoun.activityreport.ui.theme.Grey500
import com.pierrebenayoun.activityreport.ui.theme.Grey700
import com.pierrebenayoun.activityreport.ui.theme.transparent
import eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.model.SelectableFoodQuestion
import eu.benayoun.mylittlefoodquiz.ui.theme.BackgroundAndContentColor
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeColors
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeDimensions.padding2

@Composable
fun FoodQuestionsListComposable(
    modifier: Modifier = Modifier,
    foodQuestionsList: List<SelectableFoodQuestion>,
    userHasRespondedAllQuestions: Boolean
) {
    // Question List
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding2)
            .background(transparent)
    ) {

        Column(
            modifier = Modifier
                .weight(1f)
                .background(transparent)
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
        Spacer(modifier = Modifier.height(padding2))

        // send responses
        val unselectedBackgroundAndContentColor = BackgroundAndContentColor(
            ComposeColors.getColor(
                light = Grey500,
                dark = Grey700
            ),
            ComposeColors.getColor(
                ComposeColors.textOnDarkBackground(),
                ComposeColors.textOnDarkBackground()
            )
        )

        val selectedBackgroundAndContentColor = BackgroundAndContentColor(
            ComposeColors.selectedChoiceColor(),
            ComposeColors.getColor(
                light = ComposeColors.textOnDarkBackground(),
                dark = ComposeColors.textOnDarkBackground()
            )
        )

        val sendResponseBackgroundAndContentColor = if (userHasRespondedAllQuestions)
            selectedBackgroundAndContentColor else unselectedBackgroundAndContentColor
        Row(modifier = modifier
            .background(
                color = sendResponseBackgroundAndContentColor.background,
                shape = RoundedCornerShape(padding2)
            )
            .fillMaxWidth()
            .clickable(enabled = userHasRespondedAllQuestions) { }) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Envoyer les réponses",
                color = selectedBackgroundAndContentColor.content,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}
