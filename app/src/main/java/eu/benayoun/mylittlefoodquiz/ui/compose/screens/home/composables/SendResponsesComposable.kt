package eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.pierrebenayoun.activityreport.ui.theme.Grey500
import com.pierrebenayoun.activityreport.ui.theme.Grey700
import eu.benayoun.mylittlefoodquiz.ui.theme.BackgroundAndContentColor
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeColors
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeDimensions

@Composable
fun SendResponsesComposable(
    modifier: Modifier = Modifier,
    userHasRespondedAllQuestions: Boolean,
    sendResponses: () -> Unit
) {
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
            shape = RoundedCornerShape(ComposeDimensions.padding2)
        )
        .fillMaxWidth()
        .clickable(enabled = userHasRespondedAllQuestions) { sendResponses() }) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Envoyer les réponses",
            color = selectedBackgroundAndContentColor.content,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
    }
}