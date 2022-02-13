package com.allana.suitapp_challengechapter5.utils

import com.allana.suitapp_challengechapter5.R
import com.allana.suitapp_challengechapter5.databinding.ActivityGameBinding

object HelperOnClick {
    fun clickScissorLeft(binding: ActivityGameBinding) {
        binding.flPaperLeft.setBackgroundColor(0)
        binding.flScissorLeft.setBackgroundResource(R.drawable.bg_btn_blue_16dp)
        binding.flRockLeft.setBackgroundColor(0)
    }
    fun clickRockLeft(binding: ActivityGameBinding) {
        binding.flPaperLeft.setBackgroundColor(0)
        binding.flScissorLeft.setBackgroundColor(0)
        binding.flRockLeft.setBackgroundResource(R.drawable.bg_btn_blue_16dp)
    }
    fun clickPaperLeft(binding: ActivityGameBinding) {
        binding.flPaperLeft.setBackgroundResource(R.drawable.bg_btn_blue_16dp)
        binding.flScissorLeft.setBackgroundColor(0)
        binding.flRockLeft.setBackgroundColor(0)
    }
    fun clickScissorRight(binding: ActivityGameBinding) {
        binding.flPaperRight.setBackgroundColor(0)
        binding.flScissorRight.setBackgroundResource(R.drawable.bg_btn_blue_16dp)
        binding.flRockRight.setBackgroundColor(0)
    }
    fun clickRockRight(binding: ActivityGameBinding) {
        binding.flPaperRight.setBackgroundColor(0)
        binding.flScissorRight.setBackgroundColor(0)
        binding.flRockRight.setBackgroundResource(R.drawable.bg_btn_blue_16dp)
    }
    fun clickPaperRight(binding: ActivityGameBinding) {
        binding.flPaperRight.setBackgroundResource(R.drawable.bg_btn_blue_16dp)
        binding.flScissorRight.setBackgroundColor(0)
        binding.flRockRight.setBackgroundColor(0)
    }
    fun setButtonToDisableWhenOnClick(binding: ActivityGameBinding) {
        binding.flPaperLeft.setOnClickListener(null)
        binding.flScissorLeft.setOnClickListener(null)
        binding.flRockLeft.setOnClickListener(null)
        binding.flPaperRight.setOnClickListener(null)
        binding.flScissorRight.setOnClickListener(null)
        binding.flRockRight.setOnClickListener(null)
    }
    fun resetColorBackgroundButton(binding: ActivityGameBinding) {
        binding.flPaperLeft.setBackgroundColor(0)
        binding.flScissorLeft.setBackgroundColor(0)
        binding.flRockLeft.setBackgroundColor(0)
        binding.flPaperRight.setBackgroundColor(0)
        binding.flScissorRight.setBackgroundColor(0)
        binding.flRockRight.setBackgroundColor(0)
    }
}