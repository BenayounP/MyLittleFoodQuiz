package eu.benayoun.mylittlefoodquiz.data.repository.responses

import eu.benayoun.mylittlefoodquiz.data.model.business.response.FoodResponse
import kotlinx.coroutines.flow.Flow

interface ResponsesRepository {
    /***
     * Flows
     */

    // the flow with the food questions list
    suspend fun getFoodResponsesJsonFlow(): Flow<String>

    /**
     * Actions
     */

    fun sendFoodResponses(foodResponsesList: List<FoodResponse>)
}