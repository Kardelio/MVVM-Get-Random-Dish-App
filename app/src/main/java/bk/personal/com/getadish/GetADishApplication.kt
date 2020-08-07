package bk.personal.com.getadish

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GetADishApplication : Application(){

    companion object {
        const val SHARED_PREFERENCE_KEY = "dish_preference"
    }
}