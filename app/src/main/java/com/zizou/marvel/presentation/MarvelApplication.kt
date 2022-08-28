package com.zizou.marvel.presentation

import android.app.Application
import com.zizou.marvel.di.runTimeModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MarvelApplication)
            modules(runTimeModules)
        }
    }
}