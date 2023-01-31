package eu.benayoun.mylittlefoodquiz.data.repository.questions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
import java.lang.reflect.Type

class DefaultQuestionsRepository(
    private val networkFoodQuestionsSource: FoodQuestionsNetworkSource,
    private val externalScope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher
) : QuestionsRepository {
    private val updateFoodQuestionsMutex = Mutex()

    private val _foodQuestionListFlow = MutableStateFlow<List<FoodQuestion>>(listOf())
    private val gson = Gson()

    override suspend fun getFoodQuestionListFlow(): Flow<List<FoodQuestion>> = _foodQuestionListFlow

    override fun updateFoodQuestions() {
        externalScope.launch(dispatcher) {
            updateFoodQuestionsMutex.withLock() {
                val networkResponse = networkFoodQuestionsSource.getFoodQuestionsJson()
                if (networkResponse is NetworkResponse.Success) {
                    val QuestionListType: Type =
                        object : TypeToken<ArrayList<FoodQuestion?>?>() {}.type
                    val unsortedFoodQuestionsList =
                        gson.fromJson<List<FoodQuestion>>(networkResponse.json, QuestionListType)

                    /**
                     *  BACKSTAGE : How I generate data to test list sorting !
                     *  See local test of this class to understand ;)
                    val sb = StringBuilder("arrayListOf(")
                    unsortedFoodQuestionsList.mapIndexed{index, foodQuestion ->
                    sb.append(foodQuestion.toClassConstructorString())
                    if (index < unsortedFoodQuestionsList.size - 1) {
                    sb.append(",\n")
                    }
                    }
                    sb.append(")")
                    Log.d("TMP_DEBUG","***** LIST:\n $sb")
                     **/
                    val sortedQuestionList = getSortedListById(unsortedFoodQuestionsList)
                    _foodQuestionListFlow.value = sortedQuestionList
                }
            }
        }
    }

    fun getSortedListById(unsortedQuestionList: List<FoodQuestion>): List<FoodQuestion> {
        unsortedQuestionList.forEach { rawFoodQuestion: FoodQuestion ->
            rawFoodQuestion.choices = rawFoodQuestion.choices.sortedBy { it.order }
        }
        return unsortedQuestionList.sortedBy { it.order }
    }
}