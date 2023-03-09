package com.kazak.kirill.shoperset.di

import com.kazak.kirill.shoperset.data.category.CategoryRepositoryImpl
import com.kazak.kirill.shoperset.data.credentials.UserCredentialsRepositoryImpl
import com.kazak.kirill.shoperset.data.credentials.storage.UserCredentialsStorage
import com.kazak.kirill.shoperset.data.credentials.storage.UserCredentialsStorageImpl
import com.kazak.kirill.shoperset.domain.category.CategoryRepository
import com.kazak.kirill.shoperset.domain.credentials.UserCredentialsRepository
import org.koin.dsl.module

val dataModule = module {

    //UserCredentials
    single<UserCredentialsRepository> {
        UserCredentialsRepositoryImpl(userCredentialsStorage = get())
    }

    single<UserCredentialsStorage> {
        UserCredentialsStorageImpl(context = get())
    }



    //Category
    single<CategoryRepository> {
        CategoryRepositoryImpl(context = get())
    }
}