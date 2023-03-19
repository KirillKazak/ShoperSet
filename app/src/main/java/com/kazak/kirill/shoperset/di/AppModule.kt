package com.kazak.kirill.shoperset.di

import com.kazak.kirill.shoperset.ui.fragments.home.HomeViewModel
import com.kazak.kirill.shoperset.ui.fragments.logIn.LogInViewModel
import com.kazak.kirill.shoperset.ui.fragments.product.ProductViewModel
import com.kazak.kirill.shoperset.ui.fragments.profile.ProfileViewModel
import com.kazak.kirill.shoperset.ui.fragments.signIn.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<SignInViewModel>{
        SignInViewModel(
            checkUserCredentialsOnSignInUseCase = get()
        )
    }

    viewModel<LogInViewModel>{
        LogInViewModel(
            checkUserCredentialsOnLogInUseCase = get()
        )
    }

    viewModel<ProfileViewModel>{
        ProfileViewModel(
            deleteUserCredentialsUseCase = get(),
            getUserNameUseCase = get(),
            getUserPhotoUseCase = get(),
            saveUserPhotoUseCase = get()
        )
    }

    viewModel<HomeViewModel>{
        HomeViewModel(
            getLatestSearchProductUseCase = get(),
            getFlashSaleProductsUseCase = get(),
            getProductInformationUseCase = get(),
            getSearchingHintsUseCase = get(),
            getUserPhotoUseCase = get(),
        )
    }

    viewModel<ProductViewModel>{
        ProductViewModel()
    }
}