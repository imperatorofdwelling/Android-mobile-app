package com.imperatorofdwelling.android.di

import com.imperatorofdwelling.android.data.repositories.AuthRepositoryImpl
import com.imperatorofdwelling.android.data.repositories.CitiesRepositoryImpl
import com.imperatorofdwelling.android.domain.auth.repositories.AuthRepository
import com.imperatorofdwelling.android.domain.cities.repositories.CitiesRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun repositoryModule() = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<CitiesRepository> { CitiesRepositoryImpl(androidContext(), get()) }
}