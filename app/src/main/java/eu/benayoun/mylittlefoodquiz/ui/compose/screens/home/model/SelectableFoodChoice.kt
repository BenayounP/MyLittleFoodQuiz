package eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import eu.benayoun.mylittlefoodquiz.data.model.business.questions.Choice

data class SelectableFoodChoice(
    val choice: Choice,
    val isSelected: MutableState<Boolean> = mutableStateOf(false)
) {
    fun toggle() {
        isSelected.value = !isSelected.value
    }

    fun isSelected() = isSelected.value
}