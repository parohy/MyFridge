package com.parohy.myfridge.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    private val simpleDateFormat: SimpleDateFormat by lazy { SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()) }

    fun getCurrentDate(): String =
        simpleDateFormat.format(Calendar.getInstance().time)
}