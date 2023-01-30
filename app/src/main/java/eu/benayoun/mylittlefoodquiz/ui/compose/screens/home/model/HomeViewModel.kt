package eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.benayoun.mylittlefoodquiz.data.di.QuestionsRepositoryProvider
import eu.benayoun.mylittlefoodquiz.data.di.ResponseRepositoryProvider
import eu.benayoun.mylittlefoodquiz.data.model.business.questions.FoodQuestion
import eu.benayoun.mylittlefoodquiz.data.model.business.response.FoodResponse
import eu.benayoun.mylittlefoodquiz.data.repository.questions.QuestionsRepository
import eu.benayoun.mylittlefoodquiz.data.repository.responses.ResponsesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @QuestionsRepositoryProvider private val questionsRepository: QuestionsRepository,
    @ResponseRepositoryProvider private val responsesRepository: ResponsesRepository
) :
    ViewModel(), DefaultLifecycleObserver {
    private val _questionListState = MutableStateFlow<List<SelectableFoodQuestion>>(listOf())
    val questionListState: StateFlow<List<SelectableFoodQuestion>> =
        _questionListState.asStateFlow()

    val userHasRespondedAllQuestionsState: MutableState<Boolean> = mutableStateOf(false)

    init {
        getFlow()
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onResume(owner)
        questionsRepository.updateFoodQuestions()
    }

    fun onSelection() {
        userHasRespondedAllQuestionsState.value =
            _questionListState.value.all { it.userHasResponded }
    }

    fun sendResponses() {
        responsesRepository.sendFoodResponses(getResponses())
    }

    // INTERNAL COOKING

    private fun getFlow() {
        viewModelScope.launch {
            questionsRepository.getFoodQuestionListFlow().flowOn(Dispatchers.IO)
                .collect { foodQuestionList: List<FoodQuestion> ->
                    _questionListState.value =
                        foodQuestionList.map { it ->
                            SelectableFoodQuestion(
                                it,
                                onSelectionCallBack = ::onSelection
                            )
                        }
                }
        }
    }

    private fun getResponses(): List<FoodResponse> =
        _questionListState.value.map { it.toFoodResponse() }
}