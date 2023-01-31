package eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.model

import eu.benayoun.mylittlefoodquiz.data.model.business.questions.FoodQuestion
import eu.benayoun.mylittlefoodquiz.data.model.business.response.FoodResponse

class SelectableFoodQuestion(
    foodQuestion: FoodQuestion,
    private val onSelectionCallBack: () -> Unit
) {
    val id: Int = foodQuestion.id
    val multiple: Boolean = foodQuestion.multiple
    val name: String = foodQuestion.name
    val order: Int = foodQuestion.order
    val question: String = foodQuestion.question
    var choices: List<SelectableFoodChoice> =
        foodQuestion.choices.map { it -> SelectableFoodChoice(it) }

    var userHasResponded = false

    fun onSelection(choiceId: Int) {
        val selectableFoodChoiceIsNotSelected =
            !choices.filter { it.choice.id == choiceId }[0].isSelected()
        choices.forEach { selectableFoodChoice: SelectableFoodChoice ->
            if (selectableFoodChoice.choice.id == choiceId) {
                selectableFoodChoice.toggle()
            }
            // if there is no multiple response and we check,  uncheck all other choices
            else if (selectableFoodChoiceIsNotSelected && !multiple) {
                if (selectableFoodChoice.isSelected()) {
                    selectableFoodChoice.toggle()
                }
            }
        }
        userHasResponded = choices.any { it.isSelected() }
        onSelectionCallBack()
    }

    fun toFoodResponse(): FoodResponse {
        return FoodResponse(id, choices.filter { it.isSelected() }.map { it.choice.id })
    }
}