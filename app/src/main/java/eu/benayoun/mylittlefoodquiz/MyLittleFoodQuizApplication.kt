package eu.benayoun.mylittlefoodquiz

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Yes this class only exists for Hilt compatibility 🤷

@HiltAndroidApp
class MyLittleFoodQuizApplication : Application() {
}