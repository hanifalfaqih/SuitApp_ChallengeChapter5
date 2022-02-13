package com.allana.suitapp_challengechapter5.presentation.menu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.allana.suitapp_challengechapter5.R
import com.allana.suitapp_challengechapter5.databinding.ActivityMainMenuBinding
import com.allana.suitapp_challengechapter5.preference.NamePlayerPreference
import com.allana.suitapp_challengechapter5.presentation.game.GameActivity
import com.allana.suitapp_challengechapter5.presentation.dialog.InputNameDialogFragment
import com.google.android.material.snackbar.Snackbar

class MainMenuActivity : AppCompatActivity(), MainMenuActivityListener {

    private lateinit var binding: ActivityMainMenuBinding
    private val namePlayerPreference: NamePlayerPreference by lazy {
        NamePlayerPreference(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val name = namePlayerPreference.name.orEmpty()

        if (name.isEmpty()) {
            showDialogFragment()
        } else {
            setTextName(name)
        }
        setOnClickListener()
    }

    private fun showDialogFragment() {
        InputNameDialogFragment(this) { dialog ->
            dialog.dismiss()
        }.show(supportFragmentManager, null)
    }

    private fun setOnClickListener() {
        binding.ivPlayerVsComputer.setOnClickListener {
            GameActivity.startActivity(this, GameActivity.GAMEPLAY_MODE_VS_COMPUTER)
        }
        binding.ivPlayerVsPlayer.setOnClickListener {
            GameActivity.startActivity(this, GameActivity.GAMEPLAY_MODE_VS_PLAYER)
        }
    }

    override fun onReceivedNameFromDialogFragment(name: String) {
        setTextName(name)
    }

    private fun setTextName(name: String) {
        binding.tvNamePlayer.isVisible = true
        binding.tvNamePlayer.text = getString(R.string.text_greeting_choose_mode, name)
        Snackbar.make(binding.root, "Hi, $name", Snackbar.LENGTH_LONG).show()
    }

}