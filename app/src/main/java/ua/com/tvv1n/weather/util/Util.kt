package ua.com.tvv1n.weather.util

import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.Locale

class Util {

    fun dateFormatter(dateValue: String, pattern: String, inFormat: String): String {
        val format = SimpleDateFormat(pattern, Locale.US)
        val date = format.parse(dateValue)
        return DateFormat.format(inFormat, date).toString()
    }

    companion object {
        @JvmStatic
        fun newInstance() = Util()
    }
}