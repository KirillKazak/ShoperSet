package com.kazak.kirill.shoperset.di

import com.kazak.kirill.data.credentials.UserCredentialsRepositoryImpl
import com.kazak.kirill.data.credentials.storage.UserCredentialsStorage
import com.kazak.kirill.data.credentials.storage.UserCredentialsStorageImpl
import com.kazak.kirill.data.products.ProductsRepositoryImpl
import com.kazak.kirill.data.productInformation.ProductInformationRepositoryImpl
import com.kazak.kirill.data.searchingHint.SearchingHintRepositoryImpl
import com.kazak.kirill.domain.credentials.UserCredentialsRepository
import com.kazak.kirill.domain.products.ProductsRepository
import com.kazak.kirill.domain.productInformation.ProductInformationRepository
import com.kazak.kirill.domain.searchingHint.SearchingHintRepository
import org.koin.dsl.module

val dataModule = module {

    //UserCredentials
    single<UserCredentialsRepository> {
        UserCredentialsRepositoryImpl(userCredentialsStorage = get())
    }

    single<UserCredentialsStorage> {
        UserCredentialsStorageImpl(context = get())
    }





    single<ProductsRepository> { ProductsRepositoryImpl(configApi = get()) }

    single<ProductInformationRepository> {ProductInformationRepositoryImpl(configApi = get()) }

    single<SearchingHintRepository> { SearchingHintRepositoryImpl(configApi = get()) }
}