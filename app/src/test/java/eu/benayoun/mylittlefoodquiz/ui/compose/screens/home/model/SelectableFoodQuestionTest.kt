package eu.benayoun.mylittlefoodquiz.ui.compose.screens.home.model

import com.google.common.truth.Truth
import eu.benayoun.mylittlefoodquiz.data.model.business.questions.Choice
import eu.benayoun.mylittlefoodquiz.data.model.business.questions.FoodQuestion
import org.junit.After
import org.junit.Before
import org.junit.Test


internal class SelectableFoodQuestionTest {

    val questionChoices = arrayListOf<Choice>(
        Choice(
            description = "le meilleur vin du monde",
            id = 42,
            name = "Le Graves",
            order = 2
        ),
        Choice(
            description = "le deuxième meilleur vin du monde",
            id = 2,
            name = "Costières de Nîmes",
            order = 28
        )
    )


    val foodQuestion: FoodQuestion = FoodQuestion(
        questionChoices,
        id = 256,
        multiple = false,
        name = "Du viiiin",
        order = 1,
        question = "quel est le meilleur vin du monde ? "
    )

    @Before
    fun setup() {
        //nothing to do in this simple test
    }

    @After
    fun tearDown() {
        //nothing to do in this simple test
    }

    @Test
    fun toFoodResponseTest() {
        // ARRANGE
        val actualselectableFoodQuestion = SelectableFoodQuestion(foodQuestion = foodQuestion, {})

        // ACT
        actualselectableFoodQuestion.choices[0].toggle()
        val foodResponse = actualselectableFoodQuestion.toFoodResponse()

        //ASSERT
        Truth.assertWithMessage("response list size: ").that(foodResponse.choices.size).isEqualTo(1)
        Truth.assertWithMessage("id test: ").that(foodResponse.choices[0])
            .isEqualTo(questionChoices[0].id)
    }
}