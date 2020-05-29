package com.covid19.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.covid19.app.repository.MainRepository
import com.covid19.app.utils.Resource
import kotlinx.coroutines.Dispatchers

class HomeViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getHomeIndia() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getIndiaData()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}