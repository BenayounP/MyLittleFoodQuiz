package eu.benayoun.mylittlefoodquiz.data.model.business.questions

data class FoodQuestion(
    var choices: List<Choice>,
    val id: Int,
    val multiple: Boolean,
    val name: String,
    val order: Int,
    val question: String
)