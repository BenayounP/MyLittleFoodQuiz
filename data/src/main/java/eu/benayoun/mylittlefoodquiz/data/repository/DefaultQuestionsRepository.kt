package eu.benayoun.mylittlefoodquiz.data.repository

import eu.benayoun.mylittlefoodquiz.data.model.business.FoodQuestion
import eu.benayoun.mylittlefoodquiz.data.source.network.FoodQuestionsNetworkSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class DefaultQuestionsRepository(
    private val networkFoodQuestionsSource: FoodQuestionsNetworkSource,
    private val externalScope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher
) : QuestionsRepository {
    private val updateFoodQuestionsMutex = Mutex()

    override suspend fun getFoodQuestionListFlow(): Flow<List<FoodQuestion>> {
        TODO("Not yet implemented")
    }

    override fun updateFoodQuestions() {
        externalScope.launch(dispatcher) {
            updateFoodQuestionsMutex.withLock() {
                networkFoodQuestionsSource.getFoodQuestionsList()
            }
        }
    }
}