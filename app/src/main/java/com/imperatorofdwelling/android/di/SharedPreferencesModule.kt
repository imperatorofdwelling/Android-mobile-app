package com.imperatorofdwelling.android.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.imperatorofdwelling.android.data.local.preferences.SharedPreferencesDataSource
import com.imperatorofdwelling.android.data.local.preferences.SharedPreferencesDataSourceImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private const val APPLICATION_SETTINGS = "application_settings"

fun sharedPreferencesModule() = module {
    single<SharedPreferencesDataSource> {
        SharedPreferencesDataSourceImpl(
            getSharedPreferences(
                androidApplication()
            )
        )
    }
}

fun getSharedPreferences(app: Application): SharedPreferences {
    return app.getSharedPreferences(APPLICATION_SETTINGS, Context.MODE_PRIVATE)
}