package com.allana.suitapp_challengechapter5.presentation.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.allana.suitapp_challengechapter5.databinding.FragmentInputNameDialogBinding
import com.allana.suitapp_challengechapter5.preference.NamePlayerPreference
import com.allana.suitapp_challengechapter5.presentation.menu.MainMenuActivityListener

class InputNameDialogFragment(
    private val listener: MainMenuActivityListener,
    private val onButtonClick: (InputNameDialogFragment) -> Unit
) : DialogFragment() {

    private lateinit var binding: FragmentInputNameDialogBinding
    private val namePlayerPreference: NamePlayerPreference? by lazy {
        context?.let {
            NamePlayerPreference(it)
        }
    }


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
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentInputNameDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnSubmitName.setOnClickListener {
                onButtonClick(this@InputNameDialogFragment)
                namePlayerPreference?.name = etName.text.toString()
                listener.onReceivedNameFromDialogFragment(etName.text.toString())
            }
        }
    }
}