package com.allana.suitapp_challengechapter5.usecase

import com.allana.suitapp_challengechapter5.enum.DecideWinner
import com.allana.suitapp_challengechapter5.enum.Suit

interface SuitUseCase {

    fun getWinner(player: Suit, computer: Suit): DecideWinner
    fun handlePickComputer(): Suit

}