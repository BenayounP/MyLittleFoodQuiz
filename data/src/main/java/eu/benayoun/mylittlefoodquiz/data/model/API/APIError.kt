package eu.benayoun.mylittlefoodquiz.data.model.API

sealed class APIError {
    object NoInternet : APIError()
    object ToolError : APIError() // for retrofit errors for example
    object NoData : APIError() // there is no data (no movie) in the response
    class Exception(val localizedMessage: String) : APIError() // if there was some Kotlin exception
    object Unknown : APIError() // used for processing problems with serialization
}