package com.imperatorofdwelling.android.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.imperatorofdwelling.android.data.local.preferences.SharedPreferencesDataSource
import com.imperatorofdwelling.android.data.local.preferences.SharedPreferencesDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val APPLICATION_SETTINGS = "application_settings"

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences{
        return app.getSharedPreferences(APPLICATION_SETTINGS, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesDataSource(sharedPreferences: SharedPreferences): SharedPreferencesDataSource{
        return SharedPreferencesDataSourceImpl(sharedPreferences)
    }
}