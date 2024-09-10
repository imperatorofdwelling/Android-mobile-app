package com.imperatorofdwelling.android.di

import android.content.Context
import com.imperatorofdwelling.android.data.local.preferences.SharedPreferencesDataSource
import com.imperatorofdwelling.android.data.repositories.CitiesRepositoryImpl
import com.imperatorofdwelling.android.domain.repositories.CitiesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideCitiesRepository(
        @ApplicationContext context: Context,
        sharedPreferencesDataSource: SharedPreferencesDataSource
    ): CitiesRepository {
        return CitiesRepositoryImpl(context, sharedPreferencesDataSource)
    }
}