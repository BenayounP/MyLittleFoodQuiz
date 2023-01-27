package eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.pierrebenayoun.activityreport.ui.theme.BlueGrey300
import com.pierrebenayoun.activityreport.ui.theme.BlueGrey900
import eu.benayoun.mylittlefoodquiz.data.model.business.questions.FoodQuestion
import eu.benayoun.mylittlefoodquiz.ui.theme.BackgroundAndContentColor
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeColors
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeDimensions.padding2

@Composable
fun FoodQuestionComposable(foodQuestion: FoodQuestion, modifier: Modifier = Modifier) {
    val backgroundAndContentColor = BackgroundAndContentColor(
        ComposeColors.getColor(light = BlueGrey300, dark = BlueGrey900),
        ComposeColors.getColor(light = Color.White, dark = Color.White)
    )
    Column(modifier = Modifier.background(backgroundAndContentColor.background)) {
        Text(
            text = foodQuestion.name,
            modifier = Modifier.padding(padding2),
            style = MaterialTheme.typography.titleLarge,
            color = backgroundAndContentColor.content
        )
        Text(
            text = foodQuestion.question,
            modifier = Modifier.padding(padding2),
            style = MaterialTheme.typography.titleSmall,
            color = backgroundAndContentColor.content
        )

        foodQuestion.choices.forEach { choice ->
            FoodQuestionChoiceComposable(foodChoice = choice)
        }
    }
}
