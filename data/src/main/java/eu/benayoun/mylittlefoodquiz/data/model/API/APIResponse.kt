package eu.benayoun.mylittlefoodquiz.data.model.API

import eu.benayoun.mylittlefoodquiz.data.model.business.FoodQuestion

sealed class APIResponse {
    class Success(val foodQuestionsList: List<FoodQuestion>) : APIResponse()
    class Error(val tmdbAPIError: APIError) : APIResponse()
}