package com.kazak.kirill.shoperset.di

import com.kazak.kirill.shoperset.domain.credentials.useCase.*
import com.kazak.kirill.shoperset.domain.latestSearch.useCase.GetFlashSaleProductsUseCase
import com.kazak.kirill.shoperset.domain.latestSearch.useCase.GetLatestSearchProductUseCase
import com.kazak.kirill.shoperset.domain.product.useCase.GetProductInformationUseCase
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




    factory<GetLatestSearchProductUseCase> {
        GetLatestSearchProductUseCase(productsRepository = get())
    }

    factory<GetFlashSaleProductsUseCase> {
        GetFlashSaleProductsUseCase(productsRepository = get())
    }




    factory<GetProductInformationUseCase> {
        GetProductInformationUseCase(productInformationRepository = get())
    }
}