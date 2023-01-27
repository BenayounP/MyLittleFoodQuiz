package eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.composables

import androidx.compose.foundation.background
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
import eu.benayoun.mylittlefoodquiz.ui.theme.BackgroundAndContentColor
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeColors
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeDimensions.padding4
import eu.benayoun.mylittlefoodquiz.ui.theme.MyLittleFoodQuizTheme

@Composable
fun FoodQuestionChoiceComposable(foodChoice: Choice, modifier: Modifier = Modifier) {
    val backgroundAndContentColor = BackgroundAndContentColor(
        ComposeColors.getColor(
            light = MaterialTheme.colorScheme.primary,
            dark = MaterialTheme.colorScheme.primary
        ),
        ComposeColors.getColor(light = Grey300, dark = Grey300)
    )

    Column(
        modifier = modifier
            .background(
                color = backgroundAndContentColor.background,
                shape = RoundedCornerShape(padding4)
            )
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = foodChoice.name,
            color = backgroundAndContentColor.content,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(name = "Preview1", device = Devices.PIXEL_3A, showBackground = true)
@Composable
fun DefaultPreview() {
    val foodChoice = Choice(
        description = "le meilleur vin du monde",
        id = 1,
        name = "Le Graves",
        order = 0
    )
    MyLittleFoodQuizTheme {
        FoodQuestionChoiceComposable(foodChoice = foodChoice)
    }
}


