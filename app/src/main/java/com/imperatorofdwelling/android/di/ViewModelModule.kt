package com.imperatorofdwelling.android.di

import com.imperatorofdwelling.android.presentation.ui.apart_detail.ApartDetailViewModel
import com.imperatorofdwelling.android.presentation.ui.home_screen.HomeViewModel
import com.imperatorofdwelling.android.presentation.ui.sign_In.SignInViewModel
import com.imperatorofdwelling.android.presentation.ui.sign_up.SignUpViewModel
import com.imperatorofdwelling.android.presentation.ui.favorites.FavoritesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun viewModelModule() = module {
    viewModelOf(::SignUpViewModel)
    viewModelOf(::SignInViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::FavoritesViewModel)
    viewModelOf(::ApartDetailViewModel)
}