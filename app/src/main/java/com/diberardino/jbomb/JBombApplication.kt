package com.diberardino.jbomb

import android.app.Activity
import android.app.Application

class JBombApplication : Application() {
    companion object {
        lateinit var context: Application
        lateinit var activity: Activity
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}