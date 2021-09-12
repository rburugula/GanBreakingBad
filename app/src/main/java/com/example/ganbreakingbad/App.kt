package com.example.ganbreakingbad

import android.app.Application
import com.example.ganbreakingbad.di.AppComponent
import com.example.ganbreakingbad.di.DaggerAppComponent

class App : Application() {
    val appComponent: AppComponent = DaggerAppComponent.create()
}