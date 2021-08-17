package com.dat.android.gamebit.presentation.highscore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dat.android.gamebit.utils.PreferenceProvider
import com.dat.android.gamebit.R
import kotlinx.android.synthetic.main.activity_highscores.*

class HighscoresActivity : AppCompatActivity(R.layout.activity_highscores) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateUI()

        iv_back_menu_from_highscores.setOnClickListener {
            onBackPressed()
        }

        fillUserScore()
    }

    private fun fillUserScore() {
        var name = PreferenceProvider.getUserName()
        var userScore = PreferenceProvider.getUserScore()

        if (userScore != PreferenceProvider.DEFAULT_USER_SCORE){
            Log.e("LOL", name)
            tv_highscores_line_1_name.text = name
            tv_highscores_line_1_result.text = userScore.toString()
        }
    }

    private fun updateUI() {
        var backState = PreferenceProvider.getBackgroundStateSetting()
        when(backState){
            PreferenceProvider.BACKGROUND_BLUE -> {
                setBackgroundBlue()
            }
            PreferenceProvider.BACKGROUND_GREEN -> {
                setBackgroundGreen()
            }
            PreferenceProvider.BACKGROUND_RED -> {
                setBackgroundRed()
            }
        }
    }
    private fun setBackgroundGreen() {
        fl_background_highscores.setBackgroundResource(R.drawable.gradient_splash)
        fl_highscores_color_line_1.setBackgroundResource(R.drawable.ic_line_green_setting)
        fl_highscores_color_line_2.setBackgroundResource(R.drawable.ic_line_green_setting)
    }
    private fun setBackgroundRed() {
        fl_background_highscores.setBackgroundResource(R.drawable.gradient_splash_red)
        fl_highscores_color_line_1.setBackgroundResource(R.drawable.ic_line_setting_red)
        fl_highscores_color_line_2.setBackgroundResource(R.drawable.ic_line_setting_red)
    }
    private fun setBackgroundBlue() {
        fl_background_highscores.setBackgroundResource(R.drawable.gradient_splash_blue)
        fl_highscores_color_line_1.setBackgroundResource(R.drawable.ic_line_setting_blue)
        fl_highscores_color_line_2.setBackgroundResource(R.drawable.ic_line_setting_blue)
    }


}