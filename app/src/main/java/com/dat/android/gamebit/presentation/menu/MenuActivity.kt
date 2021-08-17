package com.dat.android.gamebit.presentation.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dat.android.gamebit.utils.PreferenceProvider
import com.dat.android.gamebit.R
import com.dat.android.gamebit.sound.SoundManager
import com.dat.android.gamebit.presentation.highscore.HighscoresActivity
import com.dat.android.gamebit.presentation.main.MainActivity
import com.dat.android.gamebit.presentation.settings.SettingActivity
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity(R.layout.activity_menu) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateUI()

        tv_button_play_from_menu.setOnClickListener {
            var intentGame = Intent(this@MenuActivity, MainActivity::class.java)
            startActivity(intentGame)
        }

        tv_button_settings_from_menu.setOnClickListener {
            var intentSetting = Intent(this@MenuActivity, SettingActivity::class.java)
            startActivity(intentSetting)
        }

        tv_button_highscores_from_menu.setOnClickListener {
            var intentHighscores = Intent(this@MenuActivity, HighscoresActivity::class.java)
            startActivity(intentHighscores)
        }
    }

    private fun updateUI() {
        var backState = PreferenceProvider.getBackgroundStateSetting()
        when (backState) {
            PreferenceProvider.BACKGROUND_BLUE -> {
                iv_background_menu.setImageResource(R.drawable.gradient_splash_blue)
            }
            PreferenceProvider.BACKGROUND_GREEN -> {
                iv_background_menu.setImageResource(R.drawable.gradient_splash)
            }
            PreferenceProvider.BACKGROUND_RED -> {
                iv_background_menu.setImageResource(R.drawable.gradient_splash_red)
            }
        }
    }




}