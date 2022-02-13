package com.allana.suitapp_challengechapter5.presentation.game

import androidx.lifecycle.ViewModel
import com.allana.suitapp_challengechapter5.enum.DecideWinner
import com.allana.suitapp_challengechapter5.enum.Suit
import com.allana.suitapp_challengechapter5.usecase.SuitUseCaseImpl

class GameViewModel : ViewModel() {

    private val suitCaseImpl = SuitUseCaseImpl()

    fun getWinner(player: Suit, computer: Suit): DecideWinner {
        return suitCaseImpl.getWinner(player, computer)
    }

    fun handlePickComputer(): Suit {
        return suitCaseImpl.handlePickComputer()
    }

}