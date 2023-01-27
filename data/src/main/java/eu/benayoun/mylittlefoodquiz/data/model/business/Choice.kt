package eu.benayoun.mylittlefoodquiz.data.model.business

data class Choice(
    val description: String,
    val id: Int,
    val name: String,
    val order: Int
)