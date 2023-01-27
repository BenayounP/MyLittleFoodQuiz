package eu.benayoun.mylittlefoodquiz.data.repository

import eu.benayoun.mylittlefoodquiz.data.model.API.NetworkResponse
import eu.benayoun.mylittlefoodquiz.data.model.business.questions.FoodQuestion
import eu.benayoun.mylittlefoodquiz.data.source.network.FoodQuestionsNetworkSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class DefaultQuestionsRepository(
    private val networkFoodQuestionsSource: FoodQuestionsNetworkSource,
    private val externalScope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher
) : QuestionsRepository {
    private val updateFoodQuestionsMutex = Mutex()

    private val _foodQuestionListFlow = MutableStateFlow<List<FoodQuestion>>(listOf())

    override suspend fun getFoodQuestionListFlow(): Flow<List<FoodQuestion>> {
        return _foodQuestionListFlow
    }

    override fun updateFoodQuestions() {
        externalScope.launch(dispatcher) {
            updateFoodQuestionsMutex.withLock() {
                val networkResponse = networkFoodQuestionsSource.getRawFoodQuestionsList()
                if (networkResponse is NetworkResponse.Success) {
                    val sortedQuestionList = getSortedListById(networkResponse.foodQuestionsList)
                    _foodQuestionListFlow.value = sortedQuestionList
                }
            }
        }
    }

    private fun getSortedListById(rawQuestionList: List<FoodQuestion>): List<FoodQuestion> {
        rawQuestionList.forEach { rawFoodQuestion: FoodQuestion ->
            rawFoodQuestion.choices = rawFoodQuestion.choices.sortedBy { it.order }
        }
        return rawQuestionList.sortedBy { it.order }
    }
}