package com.dat.android.gamebit.utils

import android.content.Context
import android.content.SharedPreferences
import com.dat.android.gamebit.App

object PreferenceProvider {

    private val BACKGROUND_KEY = "BACKGROUND_KEY"
    const val BACKGROUND_GREEN = 0
    const val BACKGROUND_RED = 1
    const val BACKGROUND_BLUE = 2


    var sp: SharedPreferences = App.getInstance()
        .getSharedPreferences("", Context.MODE_PRIVATE)

    var editor = sp.edit()

    fun saveBackgroundStateSetting(state: Int) {
        editor.putInt(
            BACKGROUND_KEY, state
        )
        editor.commit()
    }

    fun getBackgroundStateSetting(): Int {
        val savedStateBackground = sp.getInt(
            BACKGROUND_KEY,
            BACKGROUND_GREEN
        )!!
        return savedStateBackground
    }


    private val USER_NAME_KEY = "USER_NAME_KEY"
    val DEFAULT_USER_NAME = "Unknown user"


    fun saveUserName(name: String) {
        editor.putString(
            USER_NAME_KEY, name
        )
        editor.commit()
    }

    fun getUserName(): String {
        val name = sp.getString(
            USER_NAME_KEY,
            DEFAULT_USER_NAME
        )!!
        return name
    }


    private val USER_SCORE_KEY = "USER_SCORE_KEY"
    val DEFAULT_USER_SCORE = 5000


    fun saveUserScore(score: Int) {
        editor.putInt(
            USER_SCORE_KEY, score
        )
        editor.commit()
    }

    fun getUserScore(): Int {
        val score = sp.getInt(
            USER_SCORE_KEY,
            DEFAULT_USER_SCORE
        )!!
        return score
    }

    private val SOUND_KEY = "SOUND_KEY"
    const val SOUND_1 = 0
    const val SOUND_2 = 1
    const val SOUND_3 = 2

    fun saveSoundStateSetting(sound: Int) {
        editor.putInt(
            SOUND_KEY, sound
        )
        editor.commit()
    }

    fun getSoundStateSetting(): Int {
        val savedStateSound = sp.getInt(
            SOUND_KEY,
            SOUND_1
        )!!
        return savedStateSound
    }

    private const val KEY_LAST_URL = "KEY_LAST_URL"

    fun setLastURL(url: String) {
        editor.putString(
            KEY_LAST_URL, url
        )
        editor.commit()
    }

    fun getLastURL(): String {
        val url = sp.getString(
            KEY_LAST_URL,
            ""
        )!!
        return url
    }

    private const val KEY_URL = "KEY_URL"
    const val EMPTY_URL = "EMPTY_URL"
    const val MODERATOR_URL = "MODERATOR_URL"

    fun saveUrl(url: String) {
        editor.putString(
            KEY_URL, url
        )
        editor.commit()
    }

    fun getUrl(): String {
        val savedText = sp.getString(
            KEY_URL,
            EMPTY_URL
        )!!
        return savedText
    }

    private const val KEY_ALARM = "KEY_ALARM"
    const val STATE_SET_ALARM = "STATE_SET_ALARM"
    const val STATE_NOT_SET_ALARM = "STATE_NOT_SET_ALARM"

    fun setAlarmState(state: String) {
        editor.putString(
            KEY_ALARM, state
        )
        editor.commit()
    }

    fun getAlarmState(): String {
        val state = sp.getString(
            KEY_ALARM,
            STATE_NOT_SET_ALARM
        )!!
        return state
    }

    private const val KEY_TITLE = "KEY_TITLE"

    fun saveNotificationCounter(counter: Int) {
        editor.putInt(KEY_TITLE, counter)
        editor.commit()
    }

    fun getNotificationCounter(): Int {
        val notification = sp.getInt(KEY_TITLE, 0)!!
        return notification
    }


    private const val KEY_OF_URL = "KEY_OF_URL"

    fun saveOfUrl(url: String) {
        editor.putString(
            KEY_OF_URL, url
        )
        editor.commit()
    }

    fun getOfUrl(): String {
        val savedText = sp.getString(
            KEY_OF_URL,
            ""
        )!!
        return savedText
    }
}