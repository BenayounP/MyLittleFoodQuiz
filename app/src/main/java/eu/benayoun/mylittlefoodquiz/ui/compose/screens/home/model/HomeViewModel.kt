package eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.model

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.benayoun.mylittlefoodquiz.data.di.QuestionsRepositoryProvider
import eu.benayoun.mylittlefoodquiz.data.model.business.FoodQuestion
import eu.benayoun.mylittlefoodquiz.data.repository.QuestionsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(@QuestionsRepositoryProvider private val questionsRepository: QuestionsRepository) :
    ViewModel(), DefaultLifecycleObserver {
    private val _questionListState = MutableStateFlow<List<FoodQuestion>>(listOf())
    val questionListState: StateFlow<List<FoodQuestion>> = _questionListState.asStateFlow()

    fun updateFoodQuestions() = questionsRepository.updateFoodQuestions()


    override fun onCreate(owner: LifecycleOwner) {
        super.onResume(owner)
        updateFoodQuestions()
    }


}