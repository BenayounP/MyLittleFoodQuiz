package eu.benayoun.mylittlefoodquiz.data.source.network

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import eu.benayoun.mylittlefoodquiz.data.model.API.NetworkResponse
import eu.benayoun.mylittlefoodquiz.data.model.business.questions.FoodQuestion
import java.io.IOException
import java.io.InputStream
import java.lang.reflect.Type


// some inspiration from this site:
// https://www.bezkoder.com/java-android-read-json-file-assets-gson/

class FakeFromFileFoodQuestionsSource(val applicationContext: Context) :
    FoodQuestionsNetworkSource {
    override suspend fun getRawFoodQuestionsList(): NetworkResponse {
        val jsonString = getJsonFromAssets("fake_questions_list.json")
        val QuestionListType: Type = object : TypeToken<ArrayList<FoodQuestion?>?>() {}.type
        val FoodQuestionlist = Gson().fromJson<List<FoodQuestion>>(jsonString, QuestionListType)
        return NetworkResponse.Success(FoodQuestionlist)
    }

    fun getJsonFromAssets(fileName: String): String {
        val jsonString: String
        jsonString = try {
            val `is`: InputStream = applicationContext.assets.open(fileName)
            val size: Int = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, charset("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
            return ""
        }
        return jsonString
    }
}