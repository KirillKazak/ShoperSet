package com.kazak.kirill.shoperset.di

import com.kazak.kirill.shoperset.ui.signIn.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<SignInViewModel>{
        SignInViewModel(
            checkUserCredentialsOnSignInUseCase = get()
        )
    }
}