package com.kazak.kirill.shoperset.di

import com.kazak.kirill.shoperset.ui.home.HomeViewModel
import com.kazak.kirill.shoperset.ui.logIn.LogInViewModel
import com.kazak.kirill.shoperset.ui.profile.ProfileViewModel
import com.kazak.kirill.shoperset.ui.signIn.SignInViewModel
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
            getUserCredentialsByIdUseCase = get(),
            saveUserCredentialsUseCase = get()
        )
    }

    viewModel<HomeViewModel>{
        HomeViewModel(
            getLatestSearchProductUseCase = get(),
            getFlashSaleProductsUseCase = get()
        )
    }
}