package com.example.roomchronoapp.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomchronoapp.repository.ChronosRepository
import com.example.roomchronoapp.state.ChronoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CronometroViewModel @Inject constructor(private val repository: ChronosRepository): ViewModel() {
    var state by mutableStateOf(ChronoState())
        private set

    var chronoJob by mutableStateOf<Job?>(null)
        private set

    var tiempo by mutableStateOf(0L)
        private set

    fun getChronoById(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getChronoById(id).collect { item ->
                if (item != null) {
                    tiempo = item.chronos
                    state = state.copy(title = item.title)
                } else {
                    Log.d("Error", "El objeto chrono es nulo")
                }
            }
        }
    }

    fun onValue(value: String) {
        state = state.copy(title = value)
    }

    fun iniciar() {
        state = state.copy(
            cronometroActivo = true
        )
    }

    fun pausar() {
        state = state.copy(
            cronometroActivo = false,
            showSaveButton = true,
        )
    }

    fun detener() {
        chronoJob?.cancel()
        tiempo = 0
        state = state.copy(
            cronometroActivo = false,
            showSaveButton = false,
            showTextField = false
        )
    }

    fun showTextField() {
        state = state.copy(
            showTextField = true
        )
    }

    fun chronos() {
        if (state.cronometroActivo) {
            chronoJob?.cancel()
            chronoJob = viewModelScope.launch {
                while (true) {
                    delay(1000)
                    tiempo += 1000
                }
            }
        } else {
            chronoJob?.cancel()
        }
    }

}

















