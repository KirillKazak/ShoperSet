package com.kazak.kirill.shoperset.di

import com.kazak.kirill.shoperset.domain.credentials.useCase.*
import org.koin.dsl.module

val domainModule = module {

    factory<GetUserCredentialsListUseCase> {
        GetUserCredentialsListUseCase(userCredentialsRepository = get())
    }

    factory<GetUserCredentialsByIdUseCase> {
        GetUserCredentialsByIdUseCase(userCredentialsRepository = get())
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

    factory<CheckUserCredentialsOnLogInUseCase> {
        CheckUserCredentialsOnLogInUseCase(userCredentialsRepository = get())
    }
}