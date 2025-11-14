package com.example.tp1_singer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewmodel : ViewModel() {

    val diceState = MutableStateFlow(0)
    val onrRoll = MutableStateFlow(false)
    val diceSpeed = MutableStateFlow(200L)
    val diceTime = MutableStateFlow(5)
    val diceRepo = DiceRepository()

    fun rollDice(n: Int, speed: Long) {
        onrRoll.value = true
        viewModelScope.launch {
            diceRepo.generate(n, speed).collect { value ->
                diceState.value = value
            }
            onrRoll.value = false
        }
    }

    fun setRollSpeed(speed: Long) {
        diceSpeed.value = speed
    }

    fun setRollTime(n: Int) {
        diceTime.value = n
    }
}