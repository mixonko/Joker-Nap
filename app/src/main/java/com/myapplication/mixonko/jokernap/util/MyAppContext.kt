package com.myapplication.mixonko.jokernap.util

import android.app.Application
import android.content.Context

class MyAppContext: Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object{

        private var context: Context? = null

        fun getAppContext() = context
    }
}
