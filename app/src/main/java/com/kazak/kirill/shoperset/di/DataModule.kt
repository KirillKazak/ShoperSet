package com.kazak.kirill.shoperset.di

import com.kazak.kirill.shoperset.data.credentials.UserCredentialsRepositoryImpl
import com.kazak.kirill.shoperset.data.credentials.storage.UserCredentialsStorage
import com.kazak.kirill.shoperset.data.credentials.storage.UserCredentialsStorageImpl
import com.kazak.kirill.shoperset.data.products.ProductsRepositoryImpl
import com.kazak.kirill.shoperset.data.productInformation.ProductInformationRepositoryImpl
import com.kazak.kirill.shoperset.data.searchingHint.SearchingHintRepositoryImpl
import com.kazak.kirill.shoperset.domain.credentials.UserCredentialsRepository
import com.kazak.kirill.shoperset.domain.products.ProductsRepository
import com.kazak.kirill.shoperset.domain.productInformation.ProductInformationRepository
import com.kazak.kirill.shoperset.domain.searchingHint.SearchingHintRepository
import org.koin.dsl.module

val dataModule = module {

    //UserCredentials
    single<UserCredentialsRepository> {
        UserCredentialsRepositoryImpl(userCredentialsStorage = get())
    }

    single<UserCredentialsStorage> {
        UserCredentialsStorageImpl(context = get())
    }





    single<ProductsRepository> {
        ProductsRepositoryImpl()
    }





    single<ProductInformationRepository> {
        ProductInformationRepositoryImpl()
    }





    single<SearchingHintRepository> {
        SearchingHintRepositoryImpl()
    }
}