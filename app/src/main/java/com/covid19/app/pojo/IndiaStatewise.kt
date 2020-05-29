package com.covid19.app.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class IndiaStatewise(
    @SerializedName("active")
    @Expose
    val active: String?,

    @SerializedName("confirmed")
    @Expose
    val confirmed: String?,

    @SerializedName("deaths")
    @Expose
    val deaths: String?,

    @SerializedName("deltaconfirmed")
    @Expose
    val deltaconfirmed: String?,

    @SerializedName("deltadeaths")
    @Expose
    val deltadeaths: String?,

    @SerializedName("deltarecovered")
    @Expose
    val deltarecovered: String?,

    @SerializedName("lastupdatedtime")
    @Expose
    val lastupdatedtime: String?,

    @SerializedName("recovered")
    @Expose
    val recovered: String?,

    @SerializedName("state")
    @Expose
    val state: String?,

    @SerializedName("statecode")
    @Expose
    val statecode: String?,

    @SerializedName("statenotes")
    @Expose
    val statenotes: String?

)