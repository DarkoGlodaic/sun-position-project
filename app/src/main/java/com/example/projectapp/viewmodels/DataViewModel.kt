package com.example.projectapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectapp.data.ApiData
import com.example.projectapp.data.calculateSunriseSunset
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import java.time.LocalDate

sealed class DataViewState {
    object Loading : DataViewState()
    data class Loaded(val data: ApiData) : DataViewState()

    data class Error(val message: String, val data:List<String>) : DataViewState()
}

class DataViewModel() : ViewModel() {

    private val _state = MutableStateFlow<DataViewState>(DataViewState.Loading)
    val state: StateFlow<DataViewState> = _state

    private val _settingCalculation = MutableStateFlow(false)
    val settingCalculation: StateFlow<Boolean> = _settingCalculation

    val dataRepository:DataRepository = DataRepository()


    init {
        val tid = LocalDate.now()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.value = DataViewState.Loaded(
                    dataRepository.getData(
                        59.9139,
                        10.7522,
                        tid.toString(),
                        tid.year,
                        tid.monthValue,
                        tid.dayOfMonth
                    )
                )

            } catch (e: IOException) {
                //Oppdaterer state til Error dersom api kallet gir en feilmelding

                val dataCalculation: List<String> = calculateSunriseSunset(
                    59.9139,
                    10.7522,
                    tid.year,
                    tid.monthValue,
                    tid.dayOfMonth
                )

                _state.value = DataViewState.Error("Api", dataCalculation)

                _settingCalculation.value = true
            }

        }
    }

    fun brukKalkulering(switch:Boolean){

        _settingCalculation.value = switch
    }
}

