package com.covid19.app.retrofit

import com.covid19.app.pojo.GlobalData
import com.covid19.app.pojo.IndiaModel
import retrofit2.http.GET

interface ApiService {

    @GET("data.json")
    suspend fun get(): IndiaModel

    @GET("summary")
    suspend fun getWorldSummary(): GlobalData

}