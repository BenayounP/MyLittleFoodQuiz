package eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.model

import eu.benayoun.mylittlefoodquiz.data.model.business.questions.FoodQuestion

class SelectableFoodQuestion(foodQuestion: FoodQuestion) {
    val id: Int = foodQuestion.id
    val multiple: Boolean = foodQuestion.multiple
    val name: String = foodQuestion.name
    val order: Int = foodQuestion.order
    val question: String = foodQuestion.question
    var choices: List<SelectableFoodChoice> =
        foodQuestion.choices.map { it -> SelectableFoodChoice(it) }

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
    }


}