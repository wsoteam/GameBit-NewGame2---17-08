package com.dat.android.gamebit.presentation.main.dialogs

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.dat.android.gamebit.utils.PreferenceProvider
import com.dat.android.gamebit.R
import kotlinx.android.synthetic.main.dialog_defeat.*

class FragmentDialogDefeat : DialogFragment() {

    interface Callbacks{
        fun replay()
        fun exit()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        var defeatView: View = inflater.inflate(R.layout.dialog_defeat, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(0))
        isCancelable = false
        return defeatView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        updateUI()

        btnRetry.setOnClickListener {
            //(activity as MainActivity).replay()
            (activity as Callbacks).replay()
            dismiss()
        }
        btnQuit.setOnClickListener {
            (activity as Callbacks).exit()
            dismiss()
        }
    }

    private fun updateUI() {
        var backState = PreferenceProvider.getBackgroundStateSetting()
        when (backState) {
            PreferenceProvider.BACKGROUND_BLUE -> {
                llBackground.setBackgroundColor(resources.getColor(R.color.background_blue))
            }
            PreferenceProvider.BACKGROUND_GREEN -> {
                llBackground.setBackgroundColor(resources.getColor(R.color.background_green))
            }
            PreferenceProvider.BACKGROUND_RED -> {
                llBackground.setBackgroundColor(resources.getColor(R.color.background_red))
            }
        }
    }

}
