package com.imperatorofdwelling.android.di

import com.imperatorofdwelling.android.data.repositories.AuthRepositoryImpl
import com.imperatorofdwelling.android.data.repositories.LocationRepositoryImpl
import com.imperatorofdwelling.android.data.repositories.StaysRepositoryImpl
import com.imperatorofdwelling.android.data.repositories.UserRepositoryImpl
import com.imperatorofdwelling.android.domain.auth.repositories.AuthRepository
import com.imperatorofdwelling.android.domain.locations.repositories.LocationRepository
import com.imperatorofdwelling.android.domain.stays.repositories.StaysRepository
import com.imperatorofdwelling.android.domain.user.repositories.UserRepository
import org.koin.dsl.module

fun repositoryModule() = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<LocationRepository> { LocationRepositoryImpl(get()) }
    single<StaysRepository> { StaysRepositoryImpl() }
    single<UserRepository> { UserRepositoryImpl(get()) }
}