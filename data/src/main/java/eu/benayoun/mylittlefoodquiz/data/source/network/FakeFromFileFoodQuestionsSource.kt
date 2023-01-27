package eu.benayoun.mylittlefoodquiz.data.source.network

import android.content.Context
import android.util.Log
import eu.benayoun.mylittlefoodquiz.data.model.API.APIResponse
import java.io.IOException
import java.io.InputStream


// some inspiration from this site:
// https://www.bezkoder.com/java-android-read-json-file-assets-gson/

class FakeFromFileFoodQuestionsSource(val applicationContext: Context) :
    FoodQuestionsNetworkSource {
    override suspend fun getFoodQuestionsList(): APIResponse {
        val jsonString = getJsonFromAssets("fake_questions_list.json")
        Log.d("TMP_DEBUG", "****JSON***** \n $jsonString")
        return APIResponse.Success(listOf())
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