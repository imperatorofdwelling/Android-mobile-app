package com.imperatorofdwelling.android.di

import com.imperatorofdwelling.android.data.repositories.AuthRepositoryImpl
import com.imperatorofdwelling.android.data.repositories.CitiesRepositoryImpl
import com.imperatorofdwelling.android.domain.cities.repositories.CitiesRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun repositoryModule() = module {
    singleOf(::AuthRepositoryImpl)
    single<CitiesRepository> { CitiesRepositoryImpl(androidContext(), get()) }
}