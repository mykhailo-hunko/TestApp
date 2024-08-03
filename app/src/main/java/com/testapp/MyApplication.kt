package com.testapp

import android.app.Application
import com.testapp.data.di.dataModule
import com.testapp.domain.di.domainModule
import com.testapp.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                presentationModule,
                domainModule,
                dataModule,
            )
        }
    }
}