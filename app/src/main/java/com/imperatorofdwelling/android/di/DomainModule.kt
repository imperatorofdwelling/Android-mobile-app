package com.imperatorofdwelling.android.di

import com.imperatorofdwelling.android.domain.cities.repositories.CitiesRepository
import com.imperatorofdwelling.android.domain.cities.usecases.GetDefaultCityUseCase
import com.imperatorofdwelling.android.domain.cities.usecases.SearchCityUseCase
import com.imperatorofdwelling.android.domain.cities.usecases.SetDefaultCityUseCase
import com.imperatorofdwelling.android.domain.usecases.SignInUseCase
import com.imperatorofdwelling.android.domain.usecases.SignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Singleton
    fun provideSearchCityUseCase(citiesRepository: CitiesRepository): SearchCityUseCase {
        return SearchCityUseCase(citiesRepository)
    }

    @Provides
    @Singleton
    fun provideSignInUseCase(): SignInUseCase{
        return SignInUseCase()
    }

    @Provides
    @Singleton
    fun provideSignUpUseCase(): SignUpUseCase{
        return SignUpUseCase()
    }

    @Provides
    @Singleton
    fun provideSetDefaultCityUseCase(citiesRepository: CitiesRepository): SetDefaultCityUseCase {
        return SetDefaultCityUseCase(citiesRepository)
    }

    @Provides
    @Singleton
    fun provideGetDefaultCityUseCase(citiesRepository: CitiesRepository): GetDefaultCityUseCase {
        return GetDefaultCityUseCase(citiesRepository)
    }

}