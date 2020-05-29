package com.covid19.app.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.covid19.app.repository.MainRepository
import com.covid19.app.retrofit.ApiHelper
import com.covid19.app.ui.viewmodel.HomeViewModel
import com.covid19.app.ui.viewmodel.WorldViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(
                MainRepository(
                    apiHelper
                )
            ) as T
        }

        if (modelClass.isAssignableFrom(WorldViewModel::class.java)) {
            return WorldViewModel(
                MainRepository(
                    apiHelper
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }


}