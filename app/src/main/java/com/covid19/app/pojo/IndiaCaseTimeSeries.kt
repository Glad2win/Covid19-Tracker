package com.covid19.app.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class IndiaCaseTimeSeries(
    @SerializedName("dailyconfirmed")
    @Expose
    val dailyconfirmed: String?,

    @SerializedName("dailydeceased")
    @Expose
    val dailydeceased: String?,

    @SerializedName("dailyrecovered")
    @Expose
    val dailyrecovered: String?,

    @SerializedName("date")
    @Expose
    val date: String?,

    @SerializedName("totalconfirmed")
    @Expose
    val totalconfirmed: String?,

    @SerializedName("totaldeceased")
    @Expose
    val totaldeceased: String?,

    @SerializedName("totalrecovered")
    @Expose
    val totalrecovered: String?

)