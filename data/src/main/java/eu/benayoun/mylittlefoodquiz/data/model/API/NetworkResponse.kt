package eu.benayoun.mylittlefoodquiz.data.model.API

sealed class NetworkResponse {
    class Success(val json: String) : NetworkResponse()
    class Error(val tmdbAPIError: APIError) : NetworkResponse()
}