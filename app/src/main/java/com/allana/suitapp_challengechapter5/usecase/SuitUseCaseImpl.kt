package com.allana.suitapp_challengechapter5.usecase

import android.util.Log
import com.allana.suitapp_challengechapter5.enum.DecideWinner
import com.allana.suitapp_challengechapter5.enum.Suit

class SuitUseCaseImpl : SuitUseCase {

    companion object {
        const val TAG = "SUIT CASE"
    }

    override fun getWinner(player: Suit, computer: Suit): DecideWinner {
        return when {
            player == Suit.PAPER && computer == Suit.ROCK -> DecideWinner.PLAYER
            player == Suit.ROCK && computer == Suit.SCISSOR -> DecideWinner.PLAYER
            player == Suit.SCISSOR && computer == Suit.PAPER -> DecideWinner.PLAYER
            player == Suit.PAPER && computer == Suit.SCISSOR -> DecideWinner.ENEMY
            player == Suit.ROCK && computer == Suit.PAPER -> DecideWinner.ENEMY
            player == Suit.SCISSOR && computer == Suit.ROCK -> DecideWinner.ENEMY
            else -> {
                DecideWinner.DRAW
            }
        }
    }

    override fun handlePickComputer(): Suit {
        val pickComputer = Suit.values().random()
        Log.d(TAG, "Result Pick $pickComputer")
        return when (pickComputer) {
            Suit.ROCK -> {
                Suit.ROCK
            }
            Suit.PAPER -> {
                Suit.PAPER
            }
            else -> {
                Suit.SCISSOR
            }
        }
    }
}