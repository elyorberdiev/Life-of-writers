package com.example.adiblarhayoti.sharedPreferences

import android.content.Context
import android.content.SharedPreferences

object MyShared {

    private lateinit var myshared: SharedPreferences
    const val FILE_NAME = "writers"
    const val KEY = "language"
    const val KEY2 = "theme"

    fun init(context: Context) {
        myshared = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    }

    fun setLang(str: String) {
        val edit = myshared.edit()
        edit.putString(KEY, str)
        edit.apply()
    }

    fun getLang(): String {
        return myshared.getString(KEY, "uz")!!
    }

    fun setTheme(boolean: Boolean){
        val edit = myshared.edit()
        edit.putBoolean(KEY2,boolean).apply()
    }

    fun getTheme():Boolean{
        return myshared.getBoolean(KEY2,false)
    }
}