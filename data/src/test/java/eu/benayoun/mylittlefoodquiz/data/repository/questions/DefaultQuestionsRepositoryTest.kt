package eu.benayoun.mylittlefoodquiz.data.repository.questions

import com.google.common.truth.Truth
import eu.benayoun.mylittlefoodquiz.data.model.business.questions.Choice
import eu.benayoun.mylittlefoodquiz.data.model.business.questions.FoodQuestion
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class DefaultQuestionsRepositoryTest {

    private val emptyQuestionsSource = EmptyQuestionsSource()
    private val dispatcher = UnconfinedTestDispatcher()
    private val defaultQuestionsRepository =
        DefaultQuestionsRepository(emptyQuestionsSource, MainScope(), dispatcher)

    // Changed order of questions for the test !
    val unsortedQuestionList = arrayListOf(
        FoodQuestion(
            arrayListOf(
                Choice(
                    "La choucroute d'Alsace est une préparation alimentaire traditionnelle de choucroute de la cuisine alsacienne et de la cuisine française.",
                    27,
                    "La choucroute",
                    1
                ),
                Choice(
                    "La paella (/pa.e.la/ ou /pa.e.ja/, mot valencien signifiant « poêle (à frire) » ,) est une spécialité culinaire traditionnelle espagnole à base de riz rond",
                    25,
                    "La paella",
                    3
                ),
                Choice(
                    "La pizza est une recette de cuisine traditionnelle de la cuisine italienne, originaire de Naples à base de galette de pâte à pain.",
                    28,
                    "La pizza",
                    2
                )
            ),
            8, false, "Italia !", 5, "Quel plat symbolise l'Italie ?"
        ),
        FoodQuestion(
            arrayListOf(
                Choice("null", 36, "L'aubergine", 17),
                Choice("null", 17, "L'avocat", 14),
                Choice("null", 10, "La carotte", 11)
            ),
            6,
            false,
            "Fruit ou Légume ?",
            2,
            "Je suis un fruit trop souvent associé aux légumes. Qui suis-je ?"
        ),
        FoodQuestion(
            arrayListOf(
                Choice("null", 57, "De chou et de viande", 1),
                Choice("null", 58, "Chou et de croûte", 2),
                Choice("null", 60, "De chèvre et chou", 2)
            ),
            17, false, "Choucroute frate !", 4, "De quoi est composée la choucroute ?"
        ),
        FoodQuestion(
            arrayListOf(
                Choice("null", 60, "Chinoise", 2),
                Choice("null", 59, "Allemande", 1),
                Choice("null", 61, "Japonaise", 3)
            ),
            16, false, "J'adore les sushis", 1, "Les sushis sont d'origine :"
        ),
        FoodQuestion(
            arrayListOf(
                Choice("null", 24, "Les vétérinaires", 2),
                Choice("null", 23, "Les vegans", 1),
                Choice("null", 26, "Les végétariens", 1)
            ),
            7,
            true,
            "Les barbecue veggie c'est la vie !",
            29,
            "Comment appelle-t-on les gens qui ne mangent pas de viande ?"
        )
    )

    @Before
    fun setup() {
        //nothing to do in this simple test
    }

    @After
    fun tearDown() {
        //nothing to do in this simple test
    }

    // tests if questions are well sorted by checking question id order
    // todo improvement : add tests on choices order
    @Test
    fun getSortedListById_Test() {
        // ARRANGE
        val sortedQuestionIdList = arrayListOf(16, 6, 17, 8, 7)

        // ACT
        val sortedQuestionList = defaultQuestionsRepository.getSortedListById(unsortedQuestionList)

        // ASSERT
        sortedQuestionList.mapIndexed { index, foodQuestion ->
            // questions order
            Truth.assertWithMessage("id test: ").that(foodQuestion.id)
                .isEqualTo(sortedQuestionIdList[index])
        }
    }
}