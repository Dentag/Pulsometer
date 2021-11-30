package com.dentag.pulsometer.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dentag.pulsometer.model.RecyclerData
import com.dentag.pulsometer.repos.MeasureRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val measureRepository: MeasureRepository
) : ViewModel() {
    private val _measuresLiveData: MutableLiveData<List<RecyclerData>> = MutableLiveData()
    val measuresLiveData: LiveData<List<RecyclerData>> = _measuresLiveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                measureRepository.getMeasures().collect {
                    mapAndPostResult(it)
                }
            } catch (e: Exception) {
                //TODO:ERROR
            }
        }
    }

    fun addMeasures() {
        val measure = RecyclerData.PressureAndPulse(
            timeInMillis = Date().time,
            188,
            90,
            45
        )

        viewModelScope.launch(Dispatchers.IO) {
            measureRepository.postMeasure(measure)
        }
    }

    private suspend fun mapAndPostResult(measures: List<RecyclerData.PressureAndPulse>) {
        val result = measures.sortedBy { it.timeInMillis }.groupBy {
            val date = SimpleDateFormat("dd MMMM", Locale("RU"))
                .format(Date(it.timeInMillis))
            date
        }.flatMap {
            listOf(RecyclerData.Date(it.key)) + it.value
        }
        withContext(Dispatchers.Main) {
            _measuresLiveData.postValue(result)
        }
    }
}