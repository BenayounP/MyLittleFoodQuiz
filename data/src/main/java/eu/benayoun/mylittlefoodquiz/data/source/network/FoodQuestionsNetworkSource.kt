package eu.benayoun.mylittlefoodquiz.data.source.network

import eu.benayoun.mylittlefoodquiz.data.model.API.NetworkResponse

interface FoodQuestionsNetworkSource {
    suspend fun getRawFoodQuestionsList(): NetworkResponse
}