package com.imperatorofdwelling.android.di

import com.imperatorofdwelling.android.domain.auth.usecases.LogOutUseCase
import com.imperatorofdwelling.android.domain.auth.usecases.SignInUseCase
import com.imperatorofdwelling.android.domain.auth.usecases.SignUpUseCase
import com.imperatorofdwelling.android.domain.favorites.usecases.AddToFavouritesUseCase
import com.imperatorofdwelling.android.domain.favorites.usecases.DeleteFavouritesUseCase
import com.imperatorofdwelling.android.domain.favorites.usecases.GetAllFavouritesUseCase
import com.imperatorofdwelling.android.domain.locations.usecases.GetDefaultCityUseCase
import com.imperatorofdwelling.android.domain.locations.usecases.SearchAddressUseCase
import com.imperatorofdwelling.android.domain.locations.usecases.SearchCityUseCase
import com.imperatorofdwelling.android.domain.locations.usecases.SetDefaultCityUseCase
import com.imperatorofdwelling.android.domain.stays.usecases.GetAllStaysUseCase
import com.imperatorofdwelling.android.domain.stays.usecases.GetMainImageUseCase
import com.imperatorofdwelling.android.domain.stays.usecases.GetStayByIdUseCase
import com.imperatorofdwelling.android.domain.stays.usecases.GetStaysByLocationUseCase
import com.imperatorofdwelling.android.domain.user.usecases.EditUserAvatarUseCase
import com.imperatorofdwelling.android.domain.user.usecases.EditUserDataUseCase
import com.imperatorofdwelling.android.domain.user.usecases.GetTokenUseCase
import com.imperatorofdwelling.android.domain.user.usecases.GetUserAvatarUseCase
import com.imperatorofdwelling.android.domain.user.usecases.GetUserDataUseCase
import com.imperatorofdwelling.android.domain.user.usecases.GetUserRoleUseCase
import com.imperatorofdwelling.android.domain.user.usecases.IsRegisteredUseCase
import com.imperatorofdwelling.android.domain.user.usecases.SetUserRoleUseCase
import com.imperatorofdwelling.android.domain.stays.usecases.GetCreatingHelpUseCase
import com.imperatorofdwelling.android.domain.stays.usecases.GetAllAmenitiesUseCase
import com.imperatorofdwelling.android.domain.stays.usecases.UpdateCreatingHelpUseCase
import com.imperatorofdwelling.android.domain.locations.usecases.GetSavedAddressUseCase
import com.imperatorofdwelling.android.domain.locations.usecases.SaveAddressUseCase
import com.imperatorofdwelling.android.domain.stays.usecases.CreateStayUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

fun useCaseModule() = module {
    factoryOf(::SearchCityUseCase)
    factoryOf(::SearchAddressUseCase)
    factoryOf(::SignInUseCase)
    factoryOf(::SignUpUseCase)
    factoryOf(::SetDefaultCityUseCase)
    factoryOf(::GetDefaultCityUseCase)
    factoryOf(::GetAllStaysUseCase)
    factoryOf(::GetMainImageUseCase)
    factoryOf(::IsRegisteredUseCase)
    factoryOf(::GetTokenUseCase)
    factoryOf(::GetStaysByLocationUseCase)
    factoryOf(::AddToFavouritesUseCase)
    factoryOf(::GetAllFavouritesUseCase)
    factoryOf(::DeleteFavouritesUseCase)
    factoryOf(::GetUserDataUseCase)
    factoryOf(::EditUserDataUseCase)
    factoryOf(::LogOutUseCase)
    factoryOf(::EditUserAvatarUseCase)
    factoryOf(::GetUserAvatarUseCase)
    factoryOf(::GetStayByIdUseCase)
    factoryOf(::GetUserRoleUseCase)
    factoryOf(::SetUserRoleUseCase)
    factoryOf(::GetCreatingHelpUseCase)
    factoryOf(::UpdateCreatingHelpUseCase)
    factoryOf(::GetSavedAddressUseCase)
    factoryOf(::SaveAddressUseCase)
    factoryOf(::GetAllAmenitiesUseCase)
    factoryOf(::CreateStayUseCase)
}