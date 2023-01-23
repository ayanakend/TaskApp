package com.example.taskapp.utils

import android.content.Context
import android.content.SharedPreferences

class Preferences(context: Context) {
    private val sharedPreference : SharedPreferences = context.getSharedPreferences("preference",Context.MODE_PRIVATE)

    fun setBoardingShowed(isShow: Boolean) {
        return sharedPreference.edit().putBoolean("board", isShow).apply()
    }

    fun isBoardingShowed():Boolean {
        return sharedPreference.getBoolean("board", false)
    }

    fun getName(): String? {
        return sharedPreference.getString(PREF_SAVE_NAME, "")
    }
    fun saveName(title: String) {
         sharedPreference.edit().putString(PREF_SAVE_NAME, title).apply ()
    }

    fun getImage(): String? {
        return sharedPreference.getString(PREF_IMAGE_PROFILE, "")
    }
    fun saveImage(image: String) {
        sharedPreference.edit().putString(PREF_IMAGE_PROFILE, image).apply()
    }

    companion object{
        private const val PREF_SAVE_NAME = "name"
        private const val PREF_IMAGE_PROFILE = "image"
    }

}