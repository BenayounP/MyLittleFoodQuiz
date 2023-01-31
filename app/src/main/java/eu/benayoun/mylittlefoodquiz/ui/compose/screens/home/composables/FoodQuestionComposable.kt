package eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.pierrebenayoun.activityreport.ui.theme.Blue500
import com.pierrebenayoun.activityreport.ui.theme.Blue600
import com.pierrebenayoun.activityreport.ui.theme.Blue800
import com.pierrebenayoun.activityreport.ui.theme.Blue900
import eu.benayoun.mylittlefoodquiz.R
import eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.model.SelectableFoodQuestion
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeColors
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeDimensions.padding1
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeDimensions.padding2
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeDimensions.padding3

@Composable
fun FoodQuestionComposable(
    modifier: Modifier = Modifier,
    selectableFoodQuestion: SelectableFoodQuestion
) {
    @Composable
    fun backgroundGradientStart() = ComposeColors.getColor(light = Blue500, dark = Blue600)

    @Composable
    fun backgroundGradientEnd() = ComposeColors.getColor(light = Blue800, dark = Blue900)

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
            text = selectableFoodQuestion.name,
            modifier = Modifier
                .padding(horizontal = padding3)
                .fillMaxWidth(),
            style = MaterialTheme.typography.titleLarge,
            color = ComposeColors.textOnDarkBackground(),
            textAlign = TextAlign.Center
        )
        Text(
            text = selectableFoodQuestion.question,
            modifier = Modifier
                .padding(padding3)
                .fillMaxWidth(),
            style = MaterialTheme.typography.titleMedium,
            color = ComposeColors.textOnDarkBackground(),
            textAlign = TextAlign.Center
        )
        if (selectableFoodQuestion.multiple) {
            Text(
                text = stringResource(R.string.multiple_choices_title),
                modifier = Modifier
                    .padding(padding3)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.titleSmall,
                color = ComposeColors.textOnDarkBackground(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }

        selectableFoodQuestion.choices.forEachIndexed { choiceIndex, choice ->
            if (choiceIndex > 0) Spacer(modifier = Modifier.height(padding2))
            FoodQuestionChoiceComposable(
                modifier = Modifier.padding(horizontal = padding1),
                selectableFoodChoice = choice, selectableFoodQuestion::onSelection
            )
        }
    }
}
