package com.imperatorofdwelling.android.di

import com.imperatorofdwelling.android.domain.repositories.CitiesRepository
import com.imperatorofdwelling.android.domain.usecases.GetDefaultCityUseCase
import com.imperatorofdwelling.android.domain.usecases.SearchCityUseCase
import com.imperatorofdwelling.android.domain.usecases.SetDefaultCityUseCase
import com.imperatorofdwelling.android.domain.usecases.SignInUseCase
import com.imperatorofdwelling.android.domain.usecases.SignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {
    @Provides
    @ViewModelScoped
    fun provideSearchCityUseCase(citiesRepository: CitiesRepository): SearchCityUseCase {
        return SearchCityUseCase(citiesRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideSignInUseCase(): SignInUseCase{
        return SignInUseCase()
    }

    @Provides
    @ViewModelScoped
    fun provideSignUpUseCase(): SignUpUseCase{
        return SignUpUseCase()
    }

    @Provides
    @ViewModelScoped
    fun provideSetDefaultCityUseCase(citiesRepository: CitiesRepository): SetDefaultCityUseCase{
        return SetDefaultCityUseCase(citiesRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetDefaultCityUseCase(citiesRepository: CitiesRepository): GetDefaultCityUseCase {
        return GetDefaultCityUseCase(citiesRepository)
    }

}