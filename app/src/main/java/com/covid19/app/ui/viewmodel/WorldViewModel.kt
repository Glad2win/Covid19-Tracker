package com.covid19.app.ui.viewmodel

import androidx.lifecycle.*
import com.covid19.app.repository.MainRepository
import com.covid19.app.utils.Resource
import kotlinx.coroutines.Dispatchers

class WorldViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getWorldData() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getCountrySummary()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }


}