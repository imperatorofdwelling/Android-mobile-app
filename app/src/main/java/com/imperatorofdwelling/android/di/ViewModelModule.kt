package com.imperatorofdwelling.android.di

import com.imperatorofdwelling.android.presentation.ui.apart_detail.ApartDetailViewModel
import com.imperatorofdwelling.android.presentation.ui.home_screen.HomeViewModel
import com.imperatorofdwelling.android.presentation.ui.sign_In.SignInViewModel
import com.imperatorofdwelling.android.presentation.ui.sign_up.SignUpViewModel
import com.imperatorofdwelling.android.presentation.ui.favorites.FavoritesViewModel
import com.imperatorofdwelling.android.presentation.ui.user_profile.UserProfileViewModel
import com.imperatorofdwelling.android.presentation.ui.stay_list_screen.StayListViewModel
import com.imperatorofdwelling.android.presentation.ui.AuthViewModel
import com.imperatorofdwelling.android.presentation.ui.edit_profile.EditProfileViewModel
import com.imperatorofdwelling.android.presentation.ui.home_screen.filtration.FiltrationViewModel
import com.imperatorofdwelling.android.presentation.ui.landlord.main_screen.MainViewModel
import com.imperatorofdwelling.android.presentation.ui.landlord.creating.CreatingViewModel
import com.imperatorofdwelling.android.presentation.ui.landlord.creating.address_selection.AddressSelectionViewModel
import com.imperatorofdwelling.android.presentation.ui.landlord.my_objects.MyObjectsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun viewModelModule() = module {
    viewModelOf(::SignUpViewModel)
    viewModelOf(::SignInViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::FavoritesViewModel)
    viewModelOf(::ApartDetailViewModel)
    viewModelOf(::UserProfileViewModel)
    viewModelOf(::StayListViewModel)
    viewModelOf(::AuthViewModel)
    viewModelOf(::EditProfileViewModel)
    viewModelOf(::FiltrationViewModel)
    viewModelOf(::MainViewModel)
    viewModelOf(::CreatingViewModel)
    viewModelOf(::AddressSelectionViewModel)
    viewModelOf(::MyObjectsViewModel)
}