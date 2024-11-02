package com.imperatorofdwelling.android.di

import com.imperatorofdwelling.android.domain.cities.usecases.GetDefaultCityUseCase
import com.imperatorofdwelling.android.domain.cities.usecases.SearchCityUseCase
import com.imperatorofdwelling.android.domain.cities.usecases.SetDefaultCityUseCase
import com.imperatorofdwelling.android.domain.usecases.SignInUseCase
import com.imperatorofdwelling.android.domain.usecases.SignUpUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

fun useCaseModule() = module {
    factoryOf(::SearchCityUseCase)
    factoryOf(::SearchCityUseCase)
    factoryOf(::SignInUseCase)
    factoryOf(::SignUpUseCase)
    factoryOf(::SetDefaultCityUseCase)
    factoryOf(::GetDefaultCityUseCase)
}