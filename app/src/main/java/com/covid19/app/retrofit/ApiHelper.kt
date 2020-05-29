package com.covid19.app.retrofit

class ApiHelper(private val apiService: ApiService) {

    suspend fun getIndiaData() = apiService.get()
    suspend fun getWorldData() = apiService.getWorldSummary()
}