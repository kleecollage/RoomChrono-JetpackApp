package com.example.roomchronoapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomchronoapp.model.Chronos
import com.example.roomchronoapp.repository.ChronosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChronosViewModel @Inject constructor(private val repository: ChronosRepository): ViewModel() {
    private val _chronosList = MutableStateFlow<List<Chronos>>(emptyList())
    val chronosList = _chronosList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            // SELECT all ...
            repository.getAllChronos().collect { item ->
                if (item.isNullOrEmpty()) {
                    _chronosList.value = emptyList()
                } else {
                    _chronosList.value = item
                }
            }
        }
    }

    fun addChrono(chrono: Chronos) = viewModelScope.launch { repository.addChrono(chrono) }
    fun updateChrono(chrono: Chronos) = viewModelScope.launch { repository.updateChrono(chrono) }
    fun deleteChrono(chrono: Chronos) = viewModelScope.launch { repository.deleteChrono(chrono) }
}


















