package com.kazak.kirill.shoperset.di

import com.kazak.kirill.domain.credentials.useCase.*
import com.kazak.kirill.domain.products.useCase.GetFlashSaleProductsUseCase
import com.kazak.kirill.domain.productInformation.useCase.GetProductInformationUseCase
import com.kazak.kirill.domain.products.useCase.GetLatestSearchProductUseCase
import com.kazak.kirill.domain.searchingHint.useCase.GetSearchingHintsUseCase
import org.koin.dsl.module

val domainModule = module {

    //UserCredentials
    factory<GetUserNameUseCase> {
        GetUserNameUseCase(userCredentialsRepository = get())
    }

    factory<SaveUserPhotoUseCase> {
        SaveUserPhotoUseCase(userCredentialsRepository = get())
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



    //Products
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