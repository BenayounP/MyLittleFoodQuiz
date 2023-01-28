package eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import com.pierrebenayoun.activityreport.ui.theme.Blue500
import com.pierrebenayoun.activityreport.ui.theme.Blue700
import com.pierrebenayoun.activityreport.ui.theme.Blue900
import eu.benayoun.mylittlefoodquiz.data.model.business.questions.FoodQuestion
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeColors
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeDimensions.padding1
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeDimensions.padding2
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeDimensions.padding3

@Composable
fun FoodQuestionComposable(foodQuestion: FoodQuestion, modifier: Modifier = Modifier) {
    @Composable
    fun backgroundGradientStart() = ComposeColors.getColor(light = Blue500, dark = Blue700)

    @Composable
    fun backgroundGradientEnd() = ComposeColors.getColor(light = Blue700, dark = Blue900)

    val backgroundBrush = Brush.linearGradient(
        colors = listOf(backgroundGradientStart(), backgroundGradientEnd())
    )

    Column(
        modifier = Modifier
            .background(
                brush = backgroundBrush,
                shape = RoundedCornerShape(padding2)
            )
            .padding(padding2)
            .fillMaxWidth()
    ) {
        Text(
            text = foodQuestion.name,
            modifier = Modifier
                .padding(horizontal = padding3)
                .fillMaxWidth(),
            style = MaterialTheme.typography.titleLarge,
            color = ComposeColors.textOnDarkBackground(),
            textAlign = TextAlign.Center
        )
        Text(
            text = foodQuestion.question,
            modifier = Modifier
                .padding(padding3)
                .fillMaxWidth(),
            style = MaterialTheme.typography.titleSmall,
            color = ComposeColors.textOnDarkBackground(),
            textAlign = TextAlign.Center
        )

        foodQuestion.choices.forEachIndexed { index, choice ->
            if (index > 0) Spacer(modifier = Modifier.height(padding2))
            FoodQuestionChoiceComposable(
                foodChoice = choice,
                modifier = Modifier.padding(horizontal = padding1)
            )
        }
    }
}
