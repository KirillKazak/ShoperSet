package com.kazak.kirill.shoperset.di

import com.kazak.kirill.shoperset.data.credentials.UserCredentialsRepositoryImpl
import com.kazak.kirill.shoperset.data.credentials.storage.UserCredentialsStorage
import com.kazak.kirill.shoperset.data.credentials.storage.UserCredentialsStorageImpl
import com.kazak.kirill.shoperset.domain.credentials.UserCredentialsRepository
import org.koin.dsl.module

val dataModule = module {

    single<UserCredentialsRepository> {
        UserCredentialsRepositoryImpl(userCredentialsStorage = get())
    }

    single<UserCredentialsStorage> {
        UserCredentialsStorageImpl(context = get())
    }
}