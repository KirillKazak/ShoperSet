package com.kazak.kirill.shoperset.app

import android.app.Application
import com.kazak.kirill.shoperset.di.apiModule
import com.kazak.kirill.shoperset.di.appModule
import com.kazak.kirill.shoperset.di.dataModule
import com.kazak.kirill.shoperset.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, dataModule, domainModule, apiModule))
        }
    }
}