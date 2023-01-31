package eu.benayoun.mylittlefoodquiz.data.repository.questions

import eu.benayoun.mylittlefoodquiz.data.model.API.NetworkResponse
import eu.benayoun.mylittlefoodquiz.data.source.network.FoodQuestionsNetworkSource

class EmptyQuestionsSource : FoodQuestionsNetworkSource {
    override suspend fun getFoodQuestionsJson(): NetworkResponse {
        return NetworkResponse.Success("")
    }

    override suspend fun postFoodResponseJson(json: String) {
        // nothing to do
    }
}