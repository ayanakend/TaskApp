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

//    fun getEmail(): String? {
//        return sharedPreference.getString(PREF_SAVE_EMAIL, "")
//    }
    fun saveEmail(title: String) {
        sharedPreference.edit().putString(PREF_SAVE_EMAIL, title).apply ()
    }

//    fun getPhone(): String? {
//        return sharedPreference.getString(PREF_SAVE_PHONE, "")
//    }
    fun savePhone(title: String) {
        sharedPreference.edit().putString(PREF_SAVE_PHONE, title).apply ()
    }

//    fun getGender(): String? {
//        return sharedPreference.getString(PREF_SAVE_GENDER, "")
//    }
    fun saveGender(title: String) {
        sharedPreference.edit().putString(PREF_SAVE_GENDER, title).apply ()
    }

//    fun getDateOfBirth(): String? {
//        return sharedPreference.getString(PREF_SAVE_DATE_OF_BIRTH, "")
//    }
    fun saveDateOfBirth(title: String) {
        sharedPreference.edit().putString(PREF_SAVE_DATE_OF_BIRTH, title).apply ()
    }

    fun getImage(): String? {
        return sharedPreference.getString(PREF_IMAGE_PROFILE, "")
    }
    fun saveImage(image: String) {
        sharedPreference.edit().putString(PREF_IMAGE_PROFILE, image).apply()
    }

    companion object{
        private const val PREF_SAVE_NAME = "name"
        private const val PREF_SAVE_EMAIL = "name"
        private const val PREF_SAVE_PHONE = "name"
        private const val PREF_SAVE_GENDER = "name"
        private const val PREF_SAVE_DATE_OF_BIRTH = "name"
        private const val PREF_IMAGE_PROFILE = "image"
    }

}