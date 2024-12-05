package com.imperatorofdwelling.android

import android.app.Application
import com.imperatorofdwelling.android.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.osmdroid.config.Configuration

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        Configuration.getInstance().userAgentValue = packageName
        startKoin {
            androidContext(this@App)
            modules(appModule())
        }
    }
}