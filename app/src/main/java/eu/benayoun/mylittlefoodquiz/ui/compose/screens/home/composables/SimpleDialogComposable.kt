package eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import eu.benayoun.mylittlefoodquiz.ui.theme.ComposeColors

@Composable
fun SimpleDialogComposable(
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
    titleString: String,
    TextString: String
) {
    AlertDialog(confirmButton = {/* Empty because we just want one button*/ },
        onDismissRequest = { onClose() },
        title = { Text(text = titleString) },
        text = { Text(TextString) },
        dismissButton = {
            Button(
                onClick = { onClose() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = ComposeColors.selectedChoiceColor(),
                    contentColor = ComposeColors.textOnDarkBackground()
                )
            ) {
                Text("OK")
            }
        }
    )
}
