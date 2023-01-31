package eu.benayoun.mylittlefoodquiz.data.model.business.questions

data class Choice(
    val description: String,
    val id: Int,
    val name: String,
    val order: Int
) {
    // used for tests
    fun toClassConstructorString(): String {
        return "Choice(\"$description\",$id,\"$name\",$order)"
    }
}

