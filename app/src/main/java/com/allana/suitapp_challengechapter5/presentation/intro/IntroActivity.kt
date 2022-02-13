package com.allana.suitapp_challengechapter5.presentation.intro

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.allana.suitapp_challengechapter5.R
import com.allana.suitapp_challengechapter5.presentation.menu.MainMenuActivity
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroFragment

class IntroActivity : AppIntro2() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        //Paper
        addSlide(
            AppIntroFragment.createInstance(
                title = "Kertas",
                description = "Gunakan kertas untuk melawan batu, tapi kertas akan kalah jika bertemu gunting",
                imageDrawable = R.drawable.ic_paper,
                titleTypefaceFontRes = R.font.jungle_hope,
                descriptionTypefaceFontRes = R.font.jungle_hope,
                backgroundColorRes = R.color.pink
            )
        )

        //Scissor
        addSlide(
            AppIntroFragment.createInstance(
                title = "Gunting",
                description = "Gunakan gunting untuk melawan kertas, tapi gunting akan kalah jika bertemu batu",
                imageDrawable = R.drawable.ic_scissor,
                titleTypefaceFontRes = R.font.jungle_hope,
                descriptionTypefaceFontRes = R.font.jungle_hope,
                backgroundColorRes = R.color.pink
            )
        )

        //Rock
        addSlide(
            AppIntroFragment.createInstance(
                title = "Batu",
                description = "Gunakan batu untuk melawan gunting, tapi batu akan kalah jika bertemu kertas",
                imageDrawable = R.drawable.ic_rock,
                titleTypefaceFontRes = R.font.jungle_hope,
                descriptionTypefaceFontRes = R.font.jungle_hope,
                backgroundColorRes = R.color.pink
            )
        )

    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        navigateToMainMenu()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        navigateToMainMenu()
    }

    private fun navigateToMainMenu() {
        val intent = Intent(this, MainMenuActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}