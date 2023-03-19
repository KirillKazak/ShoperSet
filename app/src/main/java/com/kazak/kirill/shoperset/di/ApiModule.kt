package com.kazak.kirill.shoperset.di

import com.kazak.kirill.data.api.ConfigApi
import org.koin.dsl.module

val apiModule = module {

    single<ConfigApi> {
        ConfigApi()
    }
}