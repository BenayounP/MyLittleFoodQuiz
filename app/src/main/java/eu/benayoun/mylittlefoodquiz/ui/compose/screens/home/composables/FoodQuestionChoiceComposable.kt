package eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.pierrebenayoun.activityreport.ui.theme.Green600
import com.pierrebenayoun.activityreport.ui.theme.Green700
import com.pierrebenayoun.activityreport.ui.theme.Grey300
import eu.benayoun.mylittlefoodquiz.R
import eu.benayoun.mylittlefoodquiz.data.model.business.questions.Choice
import eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.model.SelectableFoodChoice
import eu.benayoun.mylittlefoodquiz.ui.theme.BackgroundAndContentColor
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeColors
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeColors.Companion.selectedChoiceColor
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeColors.Companion.textOnDarkBackground
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeColors.Companion.textOnLightBackground
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeDimensions.padding2
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeDimensions.padding3
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
    Row(
        modifier = modifier
            .background(
                color = backgroundAndContentColor.background,
                shape = RoundedCornerShape(padding2)
            )
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .padding(start = padding3)
                .weight(1f)
                .clickable { onSelection(selectableFoodChoice.choice.id) },
            text = selectableFoodChoice.choice.name,
            color = backgroundAndContentColor.content,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify
        )
        val descriptionString = selectableFoodChoice.choice.description
        if (descriptionString != null) {

            val informationColor = ComposeColors.getColor(light = Green600, dark = Green700)
            val dialogString = remember { mutableStateOf("") }
            Text(
                modifier = Modifier
                    .padding(horizontal = padding3)
                    .clickable { dialogString.value = descriptionString },
                text = stringResource(R.string.more_information),
                color = informationColor,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )

            //dialog
            if (dialogString.value != "") {
                SimpleDialogComposable(
                    titleString = stringResource(
                        id = R.string.dialog_description_title,
                        selectableFoodChoice.choice.name
                    ),
                    TextString = dialogString.value,
                    onClose = { dialogString.value = "" })
            }
        }

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



