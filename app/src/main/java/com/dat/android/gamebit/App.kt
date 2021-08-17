package com.dat.android.gamebit

import android.app.Application
import android.content.Intent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.amplitude.api.Amplitude
import com.dat.android.gamebit.sound.SoundManager


class App: Application(), LifecycleObserver{

    override fun onCreate() {
        super.onCreate()
        sInstance = this
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)

        Amplitude.getInstance()
            .initialize(this, "cc1dc2928366ee305bf01d75d176caaa")
            .enableForegroundTracking(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackgrounded() {
        SoundManager.stop()

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForegrounded() {
        SoundManager.start()
    }



    companion object {

        private lateinit var sInstance: App

        fun getInstance(): App {
            return sInstance
        }
    }

}