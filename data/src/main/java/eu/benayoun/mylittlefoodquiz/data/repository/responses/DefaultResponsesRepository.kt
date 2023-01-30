package eu.benayoun.mylittlefoodquiz.data.repository.responses

import android.util.Log
import com.google.gson.Gson
import eu.benayoun.mylittlefoodquiz.data.model.business.response.FoodResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class DefaultResponsesRepository(
    private val externalScope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher
) : ResponsesRepository {
    private val _foodResponseJsonFlow = MutableStateFlow<String>("")
    private val sendFoodResponsesMutex = Mutex()
    private val gson = Gson()

    override suspend fun getFoodResponsesJsonFlow(): Flow<String> = _foodResponseJsonFlow

    override fun sendFoodResponses(foodResponsesList: List<FoodResponse>) {
        externalScope.launch(dispatcher) {
            sendFoodResponsesMutex.withLock() {
                _foodResponseJsonFlow.value = gson.toJson(foodResponsesList)
                Log.d("TMP_DEBUG", "**** JSON RESPONSE: \n ${_foodResponseJsonFlow.value}")
            }
        }
    }

    override fun resetFoodResponses() {
        _foodResponseJsonFlow.value = ""
    }
}