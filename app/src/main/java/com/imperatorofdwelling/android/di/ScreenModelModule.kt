package com.imperatorofdwelling.android.di

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.hilt.ScreenModelFactory
import cafe.adriel.voyager.hilt.ScreenModelFactoryKey
import cafe.adriel.voyager.hilt.ScreenModelKey
import com.imperatorofdwelling.android.presentation.ui.city_selection.CitySelectionScreenModel
import com.imperatorofdwelling.android.presentation.ui.home_screen.HomeScreenModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
abstract class ScreenModelModule {

    @Binds
    @IntoMap
    @ScreenModelKey(CitySelectionScreenModel::class)
    abstract fun bindCitySelectionScreenModel(
        citySelectionScreenModel: CitySelectionScreenModel
    ): ScreenModel

    @Binds
    @IntoMap
    @ScreenModelFactoryKey(CitySelectionScreenModel.Factory::class)
    abstract fun bindCitySelectionScreenModelFactory(
        citySelectionScreenModelFactory: CitySelectionScreenModel.Factory
    ) :ScreenModelFactory


    @Binds
    @IntoMap
    @ScreenModelKey(HomeScreenModel::class)
    abstract fun bindHomeScreenModel(
        homeScreenModel: HomeScreenModel
    ): HomeScreenModel

    @Binds
    @IntoMap
    @ScreenModelFactoryKey(HomeScreenModel.Factory::class)
    abstract fun bindHomeScreenModelFactory(
        homeScreenModelFactory: HomeScreenModel.Factory
    ): ScreenModelFactory
}