package com.covid19.app.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class IndiaModel(
    @SerializedName("cases_time_series")
    @Expose
    val casesTimeSeries: List<IndiaCaseTimeSeries>?,

    @SerializedName("statewise")
    @Expose
    val statewise: List<IndiaStatewise>?,

    @SerializedName("tested")
    @Expose
    val tested: List<IndiaTested>?


)