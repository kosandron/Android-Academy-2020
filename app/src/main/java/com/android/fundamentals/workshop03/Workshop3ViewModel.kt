package com.android.fundamentals.workshop03

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.fundamentals.domain.location.Location
import com.android.fundamentals.domain.location.LocationGenerator
import kotlinx.coroutines.launch

class Workshop3ViewModel(
    private val generator: LocationGenerator
) : ViewModel() {
    private val mutableState =  MutableLiveData<Boolean>(false)
    val state: LiveData<Boolean> get() = mutableState

    private val mutableLocations = MutableLiveData<List<Location>>(emptyList())
    val locations: LiveData<List<Location>> get() = mutableLocations

    fun addNew() {
        viewModelScope.launch {
            mutableState.value = true

            val newLocation = generator.generateNewLocation()
            mutableLocations.value = mutableLocations.value?.plus(newLocation).orEmpty()

            mutableState.value = false
        }
    }
}