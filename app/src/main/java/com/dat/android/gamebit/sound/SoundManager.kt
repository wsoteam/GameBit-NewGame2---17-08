package com.dat.android.gamebit.sound

import android.media.MediaPlayer
import com.dat.android.gamebit.App
import com.dat.android.gamebit.utils.PreferenceProvider
import com.dat.android.gamebit.R

object SoundManager {

    private lateinit var mp: MediaPlayer
    var isStoped = true

    fun start() {
        var backStateSound =
            PreferenceProvider.getSoundStateSetting()
        when (backStateSound) {
            PreferenceProvider.SOUND_1 -> {
                mp = MediaPlayer.create(
                    App.getInstance().applicationContext,
                    R.raw.sound1
                )
            }
            PreferenceProvider.SOUND_2 -> {
                mp = MediaPlayer.create(
                    App.getInstance().applicationContext,
                    R.raw.sound2
                )
            }
            PreferenceProvider.SOUND_3 -> {
                mp = MediaPlayer.create(
                    App.getInstance().applicationContext,
                    R.raw.sound3
                )
            }
        }
        mp.isLooping = true
        mp.start()
        isStoped = false
    }

    fun stop() {
        isStoped = true
        mp.stop()
    }
}