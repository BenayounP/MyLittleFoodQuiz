package eu.benayoun.mylittlefoodquiz.data.model.business.questions

data class FoodQuestion(
    var choices: List<Choice>,
    val id: Int,
    val multiple: Boolean,
    val name: String,
    val order: Int,
    val question: String
) {
    fun toClassConstructorString(): String {
        val sb = StringBuilder()
        sb.append("FoodQuestion(arrayListOf(")
        choices.mapIndexed { index, choice ->
            sb.append(choice.toClassConstructorString())
            if (index < choices.size - 1) {
                sb.append(",")
            }
        }
        sb.append("),\n")
        sb.append("$id,")
        sb.append("$multiple,")
        sb.append("\"$name\",")
        sb.append("$order,")
        sb.append("\"$question\")")
        return sb.toString()
    }
}