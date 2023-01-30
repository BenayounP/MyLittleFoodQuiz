package eu.benayoun.mylittlefoodquiz.data.source.network

import eu.benayoun.mylittlefoodquiz.data.model.API.NetworkResponse

interface FoodQuestionsNetworkSource {
    suspend fun getFoodQuestionsJson(): NetworkResponse

    suspend fun postFoodResponseJson(json: String)
}