package com.kazak.kirill.shoperset.di

import com.kazak.kirill.shoperset.domain.credentials.useCase.*
import com.kazak.kirill.shoperset.domain.products.useCase.GetFlashSaleProductsUseCase
import com.kazak.kirill.shoperset.domain.products.useCase.GetLatestSearchProductUseCase
import com.kazak.kirill.shoperset.domain.products.useCase.GetProductInformationUseCase
import com.kazak.kirill.shoperset.domain.searchingHint.useCase.GetSearchingHintsUseCase
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
    factory<GetUserPhotoUseCase> {
        GetUserPhotoUseCase(userCredentialsRepository = get())
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




    factory<GetSearchingHintsUseCase> {
        GetSearchingHintsUseCase(searchingHintRepository = get())
    }
}