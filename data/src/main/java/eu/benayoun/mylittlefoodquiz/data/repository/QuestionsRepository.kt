package eu.benayoun.mylittlefoodquiz.data.repository

import eu.benayoun.mylittlefoodquiz.data.model.business.FoodQuestion
import kotlinx.coroutines.flow.Flow

interface QuestionsRepository {
    /***
     * Flows
     */

    // the flow with the food questions list
    suspend fun getFoodQuestionListFlow(): Flow<List<FoodQuestion>>

    /**
     * Actions
     */

    fun updateFoodQuestions()

}