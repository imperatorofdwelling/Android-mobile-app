package com.imperatorofdwelling.android.di

import com.imperatorofdwelling.android.domain.cities.usecases.GetDefaultCityUseCase
import com.imperatorofdwelling.android.domain.cities.usecases.SearchCityUseCase
import com.imperatorofdwelling.android.domain.cities.usecases.SetDefaultCityUseCase
import com.imperatorofdwelling.android.domain.usecases.SignInUseCase
import com.imperatorofdwelling.android.domain.usecases.SignUpUseCase
import com.imperatorofdwelling.android.presentation.ui.city_selection.CitySelectionViewModel
import com.imperatorofdwelling.android.presentation.ui.sign_In.SignInViewModel
import com.imperatorofdwelling.android.presentation.ui.sign_up.SignUpViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object ViewModelModule {
    @Provides
    @ActivityScoped
    fun provideSignUpViewModel(
        signUpUseCase: SignUpUseCase
    ): SignUpViewModel {
        return SignUpViewModel(
            signUpUseCase = signUpUseCase
        )
    }

    @Provides
    @ActivityScoped
    fun provideSignInViewModel(
        signInUseCase: SignInUseCase
    ): SignInViewModel {
        return SignInViewModel(
            signInUseCase = signInUseCase
        )
    }

    @Provides
    @ActivityScoped
    fun provideCitySelectionViewModel(
        searchCityUseCase: SearchCityUseCase,
        setDefaultCityUseCase: SetDefaultCityUseCase,
        getDefaultCityUseCase: GetDefaultCityUseCase
    ): CitySelectionViewModel {
        return CitySelectionViewModel(
            searchCityUseCase = searchCityUseCase,
            setDefaultCityUseCase = setDefaultCityUseCase,
            getDefaultCityUseCase = getDefaultCityUseCase
        )
    }
}