package com.itaycohen.interviewnews.ui_layer.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    const val TIME_PATTERN_IN = "yyyy-MM-dd'T'HH:mm:ssX"
    const val TIME_PATTERN_OUT = "EEE, MMMM d, yyyy HH:mm"

    fun format(publishedAt: String): CharSequence? {
        return try {
            val inFormat: DateFormat = SimpleDateFormat(TIME_PATTERN_IN, Locale.ENGLISH)
            inFormat.parse(publishedAt)
            val outFormat: DateFormat = SimpleDateFormat(TIME_PATTERN_OUT, Locale.ENGLISH)
            outFormat.format(inFormat.calendar.time)
        } catch (t: Throwable) {
            ""
        }
    }
}