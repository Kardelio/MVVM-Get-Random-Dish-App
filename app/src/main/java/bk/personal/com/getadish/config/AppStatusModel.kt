package bk.personal.com.getadish.config

import android.content.SharedPreferences
import bk.personal.com.getadish.utils.SharedPreferencesDelegate
import javax.inject.Inject

interface IAppStatusModel {
    var lastTimeStampButtonPressed: Long
}

class AppStatusModel @Inject constructor(val sp: SharedPreferences) : IAppStatusModel {

    override var lastTimeStampButtonPressed: Long by SharedPreferencesDelegate(sp, BUTTON_TIMESTAMP_KEY, 0L)

    companion object {
        const val BUTTON_TIMESTAMP_KEY = "LAST_TIME_BUTTON_PRESSED"
    }
}