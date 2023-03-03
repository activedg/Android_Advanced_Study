package com.example.naversearchtest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltApplication : Application() {
    companion object {
        private lateinit var application: HiltApplication
        fun getInstance() : HiltApplication = application
    }

    override fun onCreate(){
        super.onCreate()
        application = this
    }
}