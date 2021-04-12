package com.example.adiblarhayoti

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.adiblarhayoti.sharedPreferences.MyShared
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        MyShared.init(this)
        if (MyShared.getTheme()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            MyShared.setTheme(true)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            MyShared.setTheme(false)
        }
        setLocale()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }




    fun setLocale() {
        val lang = MyShared.getLang()
        val locale = Locale(lang)
        Locale.setDefault(locale)

        val config: Configuration = resources.configuration
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}