package com.imperatorofdwelling.android.di

import com.imperatorofdwelling.android.data.local.preferences.CreatingHelpManager
import com.imperatorofdwelling.android.data.local.preferences.RoleManager
import com.imperatorofdwelling.android.data.repositories.AuthRepositoryImpl
import com.imperatorofdwelling.android.data.repositories.FavouritesRepositoryImpl
import com.imperatorofdwelling.android.data.repositories.LocationRepositoryImpl
import com.imperatorofdwelling.android.data.repositories.StaysRepositoryImpl
import com.imperatorofdwelling.android.data.repositories.UserRepositoryImpl
import com.imperatorofdwelling.android.data.utils.CookieManager
import com.imperatorofdwelling.android.domain.auth.repositories.AuthRepository
import com.imperatorofdwelling.android.domain.favorites.repositories.FavouritesRepository
import com.imperatorofdwelling.android.domain.locations.repositories.LocationRepository
import com.imperatorofdwelling.android.domain.stays.repositories.StaysRepository
import com.imperatorofdwelling.android.domain.user.repositories.UserRepository
import com.imperatorofdwelling.android.presentation.ui.navigation.NavigationModel
import org.koin.dsl.module

fun repositoryModule() = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<LocationRepository> { LocationRepositoryImpl(get()) }
    single<StaysRepository> { StaysRepositoryImpl(get(), get(), get(), get(), get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get(), get()) }
    single<FavouritesRepository> { FavouritesRepositoryImpl(get()) }
    single<CookieManager> { CookieManager(get()) }
    single<NavigationModel> { NavigationModel(get()) }
    single<RoleManager> { RoleManager(get()) }
    single<CreatingHelpManager> { CreatingHelpManager(get()) }
}