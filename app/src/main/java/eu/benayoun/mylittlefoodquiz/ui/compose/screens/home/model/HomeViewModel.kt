package eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.model

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.benayoun.mylittlefoodquiz.data.di.QuestionsRepositoryProvider
import eu.benayoun.mylittlefoodquiz.data.model.business.questions.FoodQuestion
import eu.benayoun.mylittlefoodquiz.data.repository.QuestionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(@QuestionsRepositoryProvider private val questionsRepository: QuestionsRepository) :
    ViewModel(), DefaultLifecycleObserver {
    private val _questionListState = MutableStateFlow<List<SelectableFoodQuestion>>(listOf())
    val questionListState: StateFlow<List<SelectableFoodQuestion>> =
        _questionListState.asStateFlow()

    init {
        getFlow()
    }

    private fun updateFoodQuestions() = questionsRepository.updateFoodQuestions()

    override fun onCreate(owner: LifecycleOwner) {
        super.onResume(owner)
        updateFoodQuestions()
    }

    // INTERNAL COOKING

    private fun getFlow() {
        viewModelScope.launch {
            questionsRepository.getFoodQuestionListFlow().flowOn(Dispatchers.IO)
                .collect { foodQuestionList: List<FoodQuestion> ->
                    _questionListState.value =
                        foodQuestionList.map { it -> SelectableFoodQuestion(it) }
                }
        }
    }
}