package com.covid19.app.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class IndiaTested(

    @SerializedName("individualstestedperconfirmedcase")
    @Expose
    val individualstestedperconfirmedcase: String,
    @SerializedName("positivecasesfromsamplesreported")
    @Expose
    val positivecasesfromsamplesreported: String,
    @SerializedName("samplereportedtoday")
    @Expose
    val samplereportedtoday: String,
    @SerializedName("source")
    @Expose
    val source: String,
    @SerializedName("testpositivityrate")
    @Expose
    val testpositivityrate: String,
    @SerializedName("testsconductedbyprivatelabs")
    @Expose
    val testsconductedbyprivatelabs: String,
    @SerializedName("testsperconfirmedcase")
    @Expose
    val testsperconfirmedcase: String,
    @SerializedName("testspermillion")
    @Expose
    val testspermillion: String,
    @SerializedName("totalindividualstested")
    @Expose
    val totalindividualstested: String,
    @SerializedName("totalpositivecases")
    @Expose
    val totalpositivecases: String,
    @SerializedName("totalsamplestested")
    @Expose
    val totalsamplestested: String,
    @SerializedName("updatetimestamp")
    @Expose
    val updatetimestamp: String
)