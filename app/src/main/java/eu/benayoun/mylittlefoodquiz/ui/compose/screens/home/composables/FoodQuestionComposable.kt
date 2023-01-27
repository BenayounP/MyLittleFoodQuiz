package eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.pierrebenayoun.activityreport.ui.theme.Grey300
import com.pierrebenayoun.activityreport.ui.theme.Grey700
import com.pierrebenayoun.activityreport.ui.theme.Grey900
import eu.benayoun.mylittlefoodquiz.data.model.business.questions.FoodQuestion
import eu.benayoun.mylittlefoodquiz.ui.theme.BackgroundAndContentColor
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeColors
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeDimensions.padding1
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeDimensions.padding3
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeDimensions.padding4

@Composable
fun FoodQuestionComposable(foodQuestion: FoodQuestion, modifier: Modifier = Modifier) {
    val backgroundAndContentColor = BackgroundAndContentColor(
        ComposeColors.getColor(light = Grey300, dark = Grey900),
        ComposeColors.getColor(light = Grey700, dark = Grey300)
    )
    Column(
        modifier = Modifier
            .background(backgroundAndContentColor.background)
            .fillMaxWidth()
    ) {
        Text(
            text = foodQuestion.name,
            modifier = Modifier
                .padding(padding3)
                .fillMaxWidth(),
            style = MaterialTheme.typography.titleLarge,
            color = backgroundAndContentColor.content,
            textAlign = TextAlign.Center
        )
        Text(
            text = foodQuestion.question,
            modifier = Modifier
                .padding(padding3)
                .fillMaxWidth(),
            style = MaterialTheme.typography.titleSmall,
            color = backgroundAndContentColor.content,
            textAlign = TextAlign.Center
        )

        foodQuestion.choices.forEachIndexed { index, choice ->
            if (index > 0) Spacer(modifier = Modifier.height(padding4))
            FoodQuestionChoiceComposable(
                foodChoice = choice,
                modifier = Modifier.padding(horizontal = padding1)
            )
        }
    }
}
