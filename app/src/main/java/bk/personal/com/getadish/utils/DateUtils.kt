package bk.personal.com.getadish.utils

import java.util.*

class DateUtils {
   companion object {

       fun getCurrentTimeStamp(): Long {
            val cal = Calendar.getInstance()
           return cal.timeInMillis
       }

       fun convertTimeStampToDate(ts: Long): Date {
            val cal  = Calendar.getInstance().apply {
                timeInMillis = ts
            }
           return cal.time
       }

   }
}