package com.covid19.app.utils

import java.text.SimpleDateFormat
import java.util.*

object Constants {

    val ymdFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    val EEEddMMMyyyy: SimpleDateFormat = SimpleDateFormat("EEE dd-MMM-yyyy", Locale.US)

    val indiaYmdFormat: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
}