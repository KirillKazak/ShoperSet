package com.kazak.kirill.shoperset.di

import com.kazak.kirill.shoperset.domain.credentials.useCase.CheckUserCredentialsOnSignInUseCase
import com.kazak.kirill.shoperset.domain.credentials.useCase.DeleteUserCredentialsUseCase
import com.kazak.kirill.shoperset.domain.credentials.useCase.GetUserCredentialsListUseCase
import com.kazak.kirill.shoperset.domain.credentials.useCase.SaveUserCredentialsUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetUserCredentialsListUseCase> {
        GetUserCredentialsListUseCase(userCredentialsRepository = get())
    }

    factory<SaveUserCredentialsUseCase> {
        SaveUserCredentialsUseCase(userCredentialsRepository = get())
    }

    factory<DeleteUserCredentialsUseCase> {
        DeleteUserCredentialsUseCase(userCredentialsRepository = get())
    }

    factory<CheckUserCredentialsOnSignInUseCase> {
        CheckUserCredentialsOnSignInUseCase(userCredentialsRepository = get())
    }
}