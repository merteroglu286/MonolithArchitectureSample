package com.merteroglu286.monolitharchitecturesample.utility.extension

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

const val IsoFormatPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
const val IsoFormatPatternAlternate = "yyyy-MM-dd'T'HH:mm:ss"
const val FulLDateFormat = "yyyy-MM-dd HH:mm"
const val DayFormat = "yyyy-MM-dd"


fun String?.toBasicDateFormat(
    fromFormat: String = IsoFormatPattern,
    format: String = FulLDateFormat
): String {
    if (this == null)
        return ""
    val mSDF = SimpleDateFormat(format)
    val formatter = SimpleDateFormat(fromFormat)

    var result = ""
    try {
        val date = formatter.parse(this)
        val cal = Calendar.getInstance()
        cal.time = date
        result = mSDF.format(cal.time)

    } catch (e: Exception) {
        e.printStackTrace()
    }

    return result
}

fun Long?.toDateTime(datePattern: String = FulLDateFormat): String {
    if (this == null) {
        return ""
    }

    val date = Date(this)
    val format = SimpleDateFormat(datePattern)
    return format.format(date)
}

@SuppressLint("SimpleDateFormat")
fun String?.toTimeStamp(): Long {
    if (this.isNullOrEmpty()) return 0L
    kotlin.runCatching {
        return SimpleDateFormat(IsoFormatPattern).parse(this)?.time ?: 0L
    }
    return 0L

}