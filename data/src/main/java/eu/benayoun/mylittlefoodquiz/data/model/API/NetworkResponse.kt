package eu.benayoun.mylittlefoodquiz.data.model.API

import eu.benayoun.mylittlefoodquiz.data.model.business.FoodQuestion

sealed class NetworkResponse {
    class Success(val foodQuestionsList: List<FoodQuestion>) : NetworkResponse()
    class Error(val tmdbAPIError: APIError) : NetworkResponse()
}