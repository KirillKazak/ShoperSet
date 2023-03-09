package com.kazak.kirill.shoperset.di

import com.kazak.kirill.shoperset.domain.category.useCase.GetCategoryListUseCase
import com.kazak.kirill.shoperset.domain.credentials.useCase.*
import org.koin.dsl.module

val domainModule = module {

    //UserCredentials
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



    //Category
    factory<GetCategoryListUseCase> {
        GetCategoryListUseCase(categoryRepository = get())
    }
}