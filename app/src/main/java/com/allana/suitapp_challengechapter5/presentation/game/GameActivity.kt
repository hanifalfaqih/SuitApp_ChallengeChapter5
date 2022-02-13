package com.allana.suitapp_challengechapter5.presentation.game

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.allana.suitapp_challengechapter5.R
import com.allana.suitapp_challengechapter5.databinding.ActivityGameBinding
import com.allana.suitapp_challengechapter5.enum.DecideWinner
import com.allana.suitapp_challengechapter5.enum.Suit
import com.allana.suitapp_challengechapter5.enum.SuitPlayer
import com.allana.suitapp_challengechapter5.preference.NamePlayerPreference
import com.allana.suitapp_challengechapter5.presentation.dialog.ResultWinnerDialogFragment
import com.allana.suitapp_challengechapter5.presentation.menu.MainMenuActivity
import com.allana.suitapp_challengechapter5.utils.HelperOnClick
import com.allana.suitapp_challengechapter5.utils.toast

class GameActivity : AppCompatActivity(), GameActivityListener {

    companion object {
        const val TAG = "Game Activity"

        private const val EXTRAS_GAMEPLAY_MODE = "EXTRAS_GAMEPLAY_MODE"
        const val GAMEPLAY_MODE_VS_COMPUTER = 0
        const val GAMEPLAY_MODE_VS_PLAYER = 1

        fun startActivity(context: Context, gameplayMode: Int) {
            val intent = Intent(context, GameActivity::class.java)
            intent.putExtra(EXTRAS_GAMEPLAY_MODE, gameplayMode)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityGameBinding
    private lateinit var viewModel: GameViewModel

    private var gameplayMode: Int = GAMEPLAY_MODE_VS_COMPUTER
    private var playTurn: SuitPlayer = SuitPlayer.PLAYERONE

    private lateinit var setOptionsPlayerOne: Suit
    private lateinit var setOptionsPlayerTwo: Suit
    private lateinit var setResultWinner: String
    private val name: String? by lazy {
        NamePlayerPreference(this).name
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setInitialize()
        getIntentData()
        setClickPlayerOptions()
    }

    private fun setInitialize() {
        supportActionBar?.hide()
        viewModel = GameViewModel()
        binding.tvPlayer.text = name
    }

    private fun getIntentData() {
        gameplayMode = intent.extras?.getInt(EXTRAS_GAMEPLAY_MODE, 0) ?: 0
    }

    private fun setClickPlayerOptions() {
        //check if gameplay equal mode vs player or not
        if (gameplayMode == GAMEPLAY_MODE_VS_PLAYER) {
            toast(getString(R.string.player_one_turn))
            showOptionSuitPlayer(SuitPlayer.PLAYERONE, true)
            showOptionSuitPlayer(SuitPlayer.PLAYERTWO, false)
            binding.tvComputer.text = getString(R.string.player_two)
            binding.btnSwitchTurn.isVisible = true

            //set choice playerone 
            binding.flRockLeft.setOnClickListener {
                HelperOnClick.clickRockLeft(binding)
                toast(getString(R.string.player_choose_rock, name))
                setOptionsPlayerOne = Suit.ROCK
            }
            binding.flPaperLeft.setOnClickListener {
                HelperOnClick.clickPaperLeft(binding)
                toast(getString(R.string.player_choose_paper, name))
                setOptionsPlayerOne = Suit.PAPER
            }
            binding.flScissorLeft.setOnClickListener {
                HelperOnClick.clickScissorLeft(binding)
                toast(getString(R.string.player_choose_scissor, name))
                setOptionsPlayerOne = Suit.SCISSOR
            }
        } else { //this will execute when play with computer
            binding.flRockLeft.setOnClickListener {
                HelperOnClick.clickRockLeft(binding)
                toast(getString(R.string.player_choose_rock, name))
                processGameWinner(Suit.ROCK, viewModel.handlePickComputer())
            }
            binding.flPaperLeft.setOnClickListener {
                HelperOnClick.clickPaperLeft(binding)
                toast(getString(R.string.player_choose_paper, name))
                processGameWinner(Suit.PAPER, viewModel.handlePickComputer())
            }
            binding.flScissorLeft.setOnClickListener {
                HelperOnClick.clickScissorLeft(binding)
                toast(getString(R.string.player_choose_scissor, name))
                processGameWinner(Suit.SCISSOR, viewModel.handlePickComputer())
            }
        }

        binding.btnSwitchTurn.setOnClickListener {
            if (playTurn == SuitPlayer.PLAYERONE) {
                //after playerone choose, then switch turn to playertwo
                toast(getString(R.string.player_two_turn))
                playTurn = SuitPlayer.PLAYERTWO
                showOptionSuitPlayer(SuitPlayer.PLAYERONE, false)
                showOptionSuitPlayer(SuitPlayer.PLAYERTWO, true)

                //set choice playertwo
                binding.flRockRight.setOnClickListener {
                    HelperOnClick.clickRockRight(binding)
                    toast(getString(R.string.player_two_choose_rock))
                    setOptionsPlayerTwo = Suit.ROCK
                    processGameWinner(setOptionsPlayerOne, setOptionsPlayerTwo)
                }
                binding.flPaperRight.setOnClickListener {
                    HelperOnClick.clickPaperRight(binding)
                    toast(getString(R.string.player_two_choose_paper))
                    setOptionsPlayerTwo = Suit.PAPER
                    processGameWinner(setOptionsPlayerOne, setOptionsPlayerTwo)
                }
                binding.flScissorRight.setOnClickListener {
                    HelperOnClick.clickScissorRight(binding)
                    toast(getString(R.string.player_two_choose_scissor))
                    setOptionsPlayerTwo = Suit.SCISSOR
                    processGameWinner(setOptionsPlayerOne, setOptionsPlayerTwo)
                }
            }
        }

        binding.btnReset.setOnClickListener {
            resetGame()
        }

        binding.btnMainMenu.setOnClickListener {
            backToMainMenu()
        }
    }

    private fun processGameWinner(player: Suit, enemy: Suit) {
        if (gameplayMode == GAMEPLAY_MODE_VS_PLAYER) {
            resultGameWinner(viewModel.getWinner(player, enemy))
        } else {
            setColorButtonComputer(enemy)
            resultGameWinner(viewModel.getWinner(player, enemy))
        }
    }

    private fun resultGameWinner(result: DecideWinner) {
        if (gameplayMode == GAMEPLAY_MODE_VS_PLAYER) {
            when (result) {
                DecideWinner.PLAYER -> {
                    Log.d(TAG, "Winner -> Player One")
                    HelperOnClick.setButtonToDisableWhenOnClick(binding)
                    setResultWinner = "${NamePlayerPreference(this).name.toString()} Win !"
                    showOptionSuitPlayer(SuitPlayer.PLAYERONE, true)
                    showDialogFragment(setResultWinner)
                }
                DecideWinner.ENEMY -> {
                    Log.d(TAG, "Winner -> Player Two")
                    HelperOnClick.setButtonToDisableWhenOnClick(binding)
                    setResultWinner = "Player Two Win !"
                    showOptionSuitPlayer(SuitPlayer.PLAYERONE, true)
                    showDialogFragment(setResultWinner)
                }
                DecideWinner.DRAW -> {
                    Log.d(TAG, "Draw ")
                    HelperOnClick.setButtonToDisableWhenOnClick(binding)
                    setResultWinner = "Draw"
                    showOptionSuitPlayer(SuitPlayer.PLAYERONE, true)
                    showDialogFragment(setResultWinner)
                }
            }
        } else {
            when (result) {
                DecideWinner.PLAYER -> {
                    Log.d(TAG, "Winner -> Player")
                    HelperOnClick.setButtonToDisableWhenOnClick(binding)
                    setResultWinner = "${NamePlayerPreference(this).name.toString()} Win !"
                    showDialogFragment(setResultWinner)
                }
                DecideWinner.ENEMY -> {
                    Log.d(TAG, "Winner -> Computer")
                    HelperOnClick.setButtonToDisableWhenOnClick(binding)
                    setResultWinner = "Computer Win !"
                    showDialogFragment(setResultWinner)
                }
                DecideWinner.DRAW -> {
                    Log.d(TAG, "Draw")
                    HelperOnClick.setButtonToDisableWhenOnClick(binding)
                    setResultWinner = "Draw"
                    showDialogFragment(setResultWinner)
                }
            }
        }
    }

    private fun setColorButtonComputer(computer: Suit) {
        when (computer) {
            Suit.ROCK -> {
                HelperOnClick.clickRockRight(binding)
            }
            Suit.PAPER -> {
                HelperOnClick.clickPaperRight(binding)
            }
            else -> {
                HelperOnClick.clickScissorRight(binding)
            }
        }
    }

    private fun showOptionSuitPlayer(player: SuitPlayer, isVisible: Boolean) {
        if (player == SuitPlayer.PLAYERONE) {
            binding.llLeft.isVisible = isVisible
        } else {
            binding.llRight.isVisible = isVisible
        }
    }

    private fun showDialogFragment(name: String) {
        ResultWinnerDialogFragment(this, name) { dialog ->
            dialog.dismiss()
        }.show(supportFragmentManager, null)
    }

    override fun setButtonBackToMainMenu() {
        backToMainMenu()
    }

    override fun setButtonResetGame() {
        resetGame()
    }

    private fun backToMainMenu() {
        val intent = Intent(this@GameActivity, MainMenuActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun resetGame() {
        setClickPlayerOptions()
        playTurn = SuitPlayer.PLAYERONE
        HelperOnClick.resetColorBackgroundButton(binding)
    }
}