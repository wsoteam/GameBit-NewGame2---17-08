package com.dat.android.gamebit.presentation.main.dialogs

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.dat.android.gamebit.Config
import com.dat.android.gamebit.utils.PreferenceProvider
import com.dat.android.gamebit.R
import kotlinx.android.synthetic.main.fragment_dialog_win.*
import kotlinx.android.synthetic.main.fragment_dialog_win.llBackground

class FragmentDialogWin : DialogFragment() {

    interface Callbacks {
        fun endGame()
        fun continuePlay()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        var defeatView: View = inflater.inflate(R.layout.fragment_dialog_win, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(0))
        isCancelable = false
        return defeatView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        updateUI()

        btn_quit.setOnClickListener {
            saveUserName()
            (activity as Callbacks).endGame()
            dismiss()
        }
        var amount = arguments!!.getInt(Config.TAG_USER_SCORE)
        tvMoney.text = "$" + amount

        btn_continue.setOnClickListener {
            saveUserName()
            (activity as Callbacks).continuePlay()
            dismiss()
        }

        // edtName
    }

    private fun saveUserName() {
        var name = edtName.text.toString()
        if (name != "" && name != " ") {
            PreferenceProvider.saveUserName(name)
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