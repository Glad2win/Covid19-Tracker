package com.covid19.app.repository

import com.covid19.app.retrofit.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getIndiaData() = apiHelper.getIndiaData()
    suspend fun getCountrySummary() = apiHelper.getWorldData()

}