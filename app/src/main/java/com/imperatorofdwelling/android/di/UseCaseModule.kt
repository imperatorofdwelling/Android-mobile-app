package com.imperatorofdwelling.android.di

import com.imperatorofdwelling.android.domain.auth.usecases.SignInUseCase
import com.imperatorofdwelling.android.domain.auth.usecases.SignUpUseCase
import com.imperatorofdwelling.android.domain.locations.usecases.GetDefaultCityUseCase
import com.imperatorofdwelling.android.domain.locations.usecases.SearchCityUseCase
import com.imperatorofdwelling.android.domain.locations.usecases.SetDefaultCityUseCase
import com.imperatorofdwelling.android.domain.stays.usecases.GetAllStaysUseCase
import com.imperatorofdwelling.android.domain.stays.usecases.GetMainImageUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

fun useCaseModule() = module {
    factoryOf(::SearchCityUseCase)
    factoryOf(::SearchCityUseCase)
    factoryOf(::SignInUseCase)
    factoryOf(::SignUpUseCase)
    factoryOf(::SetDefaultCityUseCase)
    factoryOf(::GetDefaultCityUseCase)
    factoryOf(::GetAllStaysUseCase)
    factoryOf(::GetMainImageUseCase)
}