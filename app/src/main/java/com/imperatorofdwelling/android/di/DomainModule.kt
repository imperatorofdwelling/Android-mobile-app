package com.imperatorofdwelling.android.di

import com.imperatorofdwelling.android.domain.repositories.CitiesRepository
import com.imperatorofdwelling.android.domain.use_cases.SearchCityUseCase
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
}