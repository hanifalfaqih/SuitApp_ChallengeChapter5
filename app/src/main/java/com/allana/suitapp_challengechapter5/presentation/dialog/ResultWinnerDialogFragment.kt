package com.allana.suitapp_challengechapter5.presentation.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.allana.suitapp_challengechapter5.databinding.FragmentResultWinnerDialogBinding
import com.allana.suitapp_challengechapter5.presentation.game.GameActivityListener

class ResultWinnerDialogFragment(
    private val listener: GameActivityListener,
    private var setResultWinner: String,
    private var onButtonClick: (ResultWinnerDialogFragment) -> Unit
) : DialogFragment() {

    private lateinit var binding: FragmentResultWinnerDialogBinding

    override fun onResume() {
        super.onResume()
        val params: WindowManager.LayoutParams? = dialog?.window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        // request a window without the title
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentResultWinnerDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvResultWinner.text = setResultWinner
            btnMainMenu.setOnClickListener {
                onButtonClick(this@ResultWinnerDialogFragment)
                listener.setButtonBackToMainMenu()
            }
            btnReset.setOnClickListener {
                onButtonClick(this@ResultWinnerDialogFragment)
                listener.setButtonResetGame()
            }
        }
    }
}