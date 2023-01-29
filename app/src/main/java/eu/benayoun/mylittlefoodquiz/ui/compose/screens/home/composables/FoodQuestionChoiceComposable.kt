package eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.pierrebenayoun.activityreport.ui.theme.Grey300
import eu.benayoun.mylittlefoodquiz.data.model.business.questions.Choice
import eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.model.SelectableFoodChoice
import eu.benayoun.mylittlefoodquiz.ui.theme.BackgroundAndContentColor
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeColors
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeColors.Companion.selectedChoiceColor
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeColors.Companion.textOnDarkBackground
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeColors.Companion.textOnLightBackground
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeDimensions.padding2
import eu.benayoun.mylittlefoodquiz.ui.theme.MyLittleFoodQuizTheme

@Composable
fun FoodQuestionChoiceComposable(
    modifier: Modifier = Modifier,
    selectableFoodChoice: SelectableFoodChoice,
    onSelection: (choiceId: Int) -> Unit
) {

    val unselectedBackgroundAndContentColor = BackgroundAndContentColor(
        ComposeColors.getColor(
            light = Grey300,
            dark = Grey300
        ),
        ComposeColors.getColor(textOnLightBackground(), textOnLightBackground())
    )

    val selectedBackgroundAndContentColor = BackgroundAndContentColor(
        selectedChoiceColor(),
        ComposeColors.getColor(light = textOnDarkBackground(), dark = textOnDarkBackground())
    )

    val backgroundAndContentColor = if (selectableFoodChoice.isSelected.value)
        selectedBackgroundAndContentColor else unselectedBackgroundAndContentColor

    Column(
        modifier = modifier
            .background(
                color = backgroundAndContentColor.background,
                shape = RoundedCornerShape(padding2)
            )
            .fillMaxWidth()
            .clickable { onSelection(selectableFoodChoice.choice.id) }
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = selectableFoodChoice.choice.name,
            color = backgroundAndContentColor.content,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}

val previewFoodChoice = Choice(
    description = "le meilleur vin du monde",
    id = 1,
    name = "Le Graves",
    order = 0
)

val unselectedPreviewFoodChoice = SelectableFoodChoice(previewFoodChoice)


fun emptyOnSelection(choiceId: Int) {}

@Preview(name = "Unselected", device = Devices.PIXEL_3A, showBackground = true)
@Composable
fun UnselectedPreview() {
    MyLittleFoodQuizTheme {
        FoodQuestionChoiceComposable(
            selectableFoodChoice = unselectedPreviewFoodChoice,
            onSelection = ::emptyOnSelection
        )
    }
}

val selectedPreviewFoodChoice = SelectableFoodChoice(previewFoodChoice).apply { toggle() }

@Preview(name = "Selected", device = Devices.PIXEL_3A, showBackground = true)
@Composable
fun SelectedPreview() {
    MyLittleFoodQuizTheme {
        FoodQuestionChoiceComposable(
            selectableFoodChoice = selectedPreviewFoodChoice,
            onSelection = ::emptyOnSelection
        )
    }
}



