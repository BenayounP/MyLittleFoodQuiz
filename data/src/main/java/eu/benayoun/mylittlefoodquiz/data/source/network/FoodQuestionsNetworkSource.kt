package eu.benayoun.mylittlefoodquiz.data.source.network

import eu.benayoun.mylittlefoodquiz.data.model.API.APIResponse

interface FoodQuestionsNetworkSource {
    suspend fun getFoodQuestionsList(): APIResponse
}