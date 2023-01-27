package eu.benayoun.mylittlefoodquiz.data.model.business

data class FoodQuestion(
    val choices: List<Choice>,
    val id: Int,
    val multiple: Boolean,
    val name: String,
    val order: Int,
    val question: String
)